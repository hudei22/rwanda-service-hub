package com.example.data

import android.util.Log
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object GeminiAssistantService {
    private const val TAG = "GeminiAssistant"
    private const val MODEL = "gemini-3.5-flash"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/$MODEL:generateContent"

    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun askAssistant(prompt: String, lang: AppLanguage): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY" || apiKey == "GEMINI_API_KEY") {
            Log.e(TAG, "Gemini API key is empty or placeholder: $apiKey")
            return@withContext when(lang) {
                AppLanguage.EN -> "AI Assistant key is not configured. Please add your GEMINI_API_KEY in the Secrets panel."
                AppLanguage.RW -> "Inyifuzo ya AI ntifite urufunguzo rwayo. Kora kuri 'Secrets panel' ubashe gushiramo GEMINI_API_KEY mukanya."
                AppLanguage.FR -> "La clé API Gemini n'est pas configurée. Veuillez ajouter votre GEMINI_API_KEY dans le panneau des secrets."
            }
        }

        val systemInstructionText = "You are 'AI Citizen Guide', a virtual assistant inside the Rwanda Service Hub app. " +
                "Your job is to guide citizens on Rwandan public services, administrative files, health clinic procedures, " +
                "opportunities, and common municipal guidelines. Keep your responses structured with bullet points or clear steps. " +
                "Provide helpful details in the language the user asks: English, Kinyarwanda, or French."

        try {
            val requestJson = JSONObject().apply {
                val contentsArray = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val partsArray = JSONArray().apply {
                            val partObj = JSONObject().apply {
                                put("text", prompt)
                            }
                            put(partObj)
                        }
                        put("parts", partsArray)
                    }
                    put(contentObj)
                }
                put("contents", contentsArray)

                val systemInstructionObj = JSONObject().apply {
                    val partsArray = JSONArray().apply {
                        val partObj = JSONObject().apply {
                            put("text", systemInstructionText)
                        }
                        put(partObj)
                    }
                    put("parts", partsArray)
                }
                put("systemInstruction", systemInstructionObj)
            }

            val requestBody = requestJson.toString().toRequestBody("application/json".toMediaType())
            val url = "$BASE_URL?key=$apiKey"

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    val errorBody = response.body?.string() ?: ""
                    Log.e(TAG, "Request failed with code ${response.code}: $errorBody")
                    throw Exception("API Error Code ${response.code}")
                }

                val responseBodyStr = response.body?.string() ?: ""
                val responseJson = JSONObject(responseBodyStr)
                val candidates = responseJson.getJSONArray("candidates")
                if (candidates.length() > 0) {
                    val firstCandidate = candidates.getJSONObject(0)
                    val contentObj = firstCandidate.getJSONObject("content")
                    val partsArray = contentObj.getJSONArray("parts")
                    if (partsArray.length() > 0) {
                        return@withContext partsArray.getJSONObject(0).getString("text")
                    }
                }
                return@withContext "Error: No valid content returned by the Gemini model."
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error invoking Gemini API", e)
            val baseMsg = when(lang) {
                AppLanguage.EN -> "Sorry, I am facing connectivity issues. Please try again or check your connectivity."
                AppLanguage.RW -> "Mumbabarire, habaye ikibazo mu muhanda wa interineti. Ongera ugerageze mukanya."
                AppLanguage.FR -> "Désolé, je rencontre des problèmes de connexion. Veuillez réessayer."
            }
            return@withContext "$baseMsg\n\nDetail: ${e.message}"
        }
    }
}
