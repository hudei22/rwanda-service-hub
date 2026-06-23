package com.example.data

enum class AppLanguage {
    EN, RW, FR
}

object TranslationData {

    fun getUiString(key: String, lang: AppLanguage): String {
        return uiStrings[key]?.get(lang) ?: key
    }

    private val uiStrings = mapOf(
        "app_title" to mapOf(
            AppLanguage.EN to "Rwanda Service Hub",
            AppLanguage.RW to "Umuyoboro wa Serivisi",
            AppLanguage.FR to "Hub de Services Rwanda"
        ),
        "app_subtitle" to mapOf(
            AppLanguage.EN to "All essential services & tools in one place",
            AppLanguage.RW to "Serivisi n'amakuru yose mu ntoki zawe",
            AppLanguage.FR to "Tous les services & infos essentiels au même endroit"
        ),
        "tab_services" to mapOf(
            AppLanguage.EN to "Gov Services",
            AppLanguage.RW to "Serivisi za Leta",
            AppLanguage.FR to "Services Publics"
        ),
        "tab_reminders" to mapOf(
            AppLanguage.EN to "Track & Remind",
            AppLanguage.RW to "Gukurikirana",
            AppLanguage.FR to "Rappels"
        ),
        "tab_opportunities" to mapOf(
            AppLanguage.EN to "Opportunities",
            AppLanguage.RW to "Amahirwe",
            AppLanguage.FR to "Opportunités"
        ),
        "tab_civil" to mapOf(
            AppLanguage.EN to "Civil Desk",
            AppLanguage.RW to "Amakuru & Ubutabazi",
            AppLanguage.FR to "Infos & Secours"
        ),
        "tab_assistant" to mapOf(
            AppLanguage.EN to "AI Citizen Guide",
            AppLanguage.RW to "Umuvuzi wa AI",
            AppLanguage.FR to "Guide Citoyen IA"
        ),
        "button_add_reminder" to mapOf(
            AppLanguage.EN to "Add New Reminder",
            AppLanguage.RW to "Ongeramo Urwibutso",
            AppLanguage.FR to "Ajouter un Rappel"
        ),
        "empty_reminders" to mapOf(
            AppLanguage.EN to "No trackings or reminders found. Tap below to add one!",
            AppLanguage.RW to "Nta nshingano cyangwa inyibutso dufite. Kanda hano wongereho!",
            AppLanguage.FR to "Aucun rappel enregistré. Appuyez ci-dessous pour en ajouter !"
        ),
        "form_title_doc" to mapOf(
            AppLanguage.EN to "Register Document Renewal",
            AppLanguage.RW to "Ongeraho Icyangombwa",
            AppLanguage.FR to "Enregistrer le Renouvellement"
        ),
        "form_title_health" to mapOf(
            AppLanguage.EN to "Schedule Health Appointment",
            AppLanguage.RW to "Tegura Gahunda Y'ubuzima",
            AppLanguage.FR to "Planifier un RDV Médical"
        ),
        "form_title_utility" to mapOf(
            AppLanguage.EN to "Track Utility Payment",
            AppLanguage.RW to "Gukurikirana Fagitire z'Ingufu",
            AppLanguage.FR to "Suivi de Facture Électricité/Eau"
        ),
        "doc_type" to mapOf(
            AppLanguage.EN to "Document Type",
            AppLanguage.RW to "Ubwoko bw'Icyangombwa",
            AppLanguage.FR to "Type de Document"
        ),
        "doc_number" to mapOf(
            AppLanguage.EN to "Document ID / Number",
            AppLanguage.RW to "Inomero y'Icyangombwa",
            AppLanguage.FR to "Numéro du Document"
        ),
        "holder_name" to mapOf(
            AppLanguage.EN to "Holder Full Name",
            AppLanguage.RW to "Amazina y'Ufite Icyangombwa",
            AppLanguage.FR to "Nom Complet du Titulaire"
        ),
        "expiry_date" to mapOf(
            AppLanguage.EN to "Expiration Date",
            AppLanguage.RW to "Itariki y'Irangira",
            AppLanguage.FR to "Date d'Expiration"
        ),
        "health_reason" to mapOf(
            AppLanguage.EN to "Purpose / Reason (e.g., General Checkup)",
            AppLanguage.RW to "Impamvu (urugero: Gusuzumisha amaso)",
            AppLanguage.FR to "Raison du RDV (ex: Visite de routine)"
        ),
        "health_facility" to mapOf(
            AppLanguage.EN to "Hospital / Health Center",
            AppLanguage.RW to "Ibitaro / Ikigo cy'Ubuzima",
            AppLanguage.FR to "Hôpital / Centre de Santé"
        ),
        "health_date" to mapOf(
            AppLanguage.EN to "Appointment Date",
            AppLanguage.RW to "Itariki ya Gahunda",
            AppLanguage.FR to "Date du Rendez-vous"
        ),
        "health_time" to mapOf(
            AppLanguage.EN to "Appointment Time",
            AppLanguage.RW to "Isaha ya Gahunda",
            AppLanguage.FR to "Heure du Rendez-vous"
        ),
        "health_notes" to mapOf(
            AppLanguage.EN to "Additional Notes / Symptoms",
            AppLanguage.RW to "Ibindi bisobanuro",
            AppLanguage.FR to "Notes Additionnelles"
        ),
        "utility_type" to mapOf(
            AppLanguage.EN to "Utility Type",
            AppLanguage.RW to "Ubwoko bw'Ingufu/Mazi",
            AppLanguage.FR to "Type de Service"
        ),
        "meter_number" to mapOf(
            AppLanguage.EN to "Meter / Account Number",
            AppLanguage.RW to "Inomero ya Mubazi/Konti",
            AppLanguage.FR to "Numéro de Compteur / Compte"
        ),
        "utility_amount" to mapOf(
            AppLanguage.EN to "Amount (RWF) (Optional)",
            AppLanguage.RW to "Amafaranga (Frw)",
            AppLanguage.FR to "Montant (RWF) (Optionnel)"
        ),
        "due_date" to mapOf(
            AppLanguage.EN to "Payment Due Date",
            AppLanguage.RW to "Itariki Ntarengwa yo Kwishyura",
            AppLanguage.FR to "Date d'Échéance"
        ),
        "btn_cancel" to mapOf(
            AppLanguage.EN to "Cancel",
            AppLanguage.RW to "Subika",
            AppLanguage.FR to "Annuler"
        ),
        "btn_save" to mapOf(
            AppLanguage.EN to "Save & Monitor",
            AppLanguage.RW to "Bika & Kurikirana",
            AppLanguage.FR to "Enregistrer"
        ),
        "search_placeholder" to mapOf(
            AppLanguage.EN to "SearchGov / Info Portal...",
            AppLanguage.RW to "Shakisha hano...",
            AppLanguage.FR to "Rechercher un service..."
        ),
        "irembo_hint" to mapOf(
            AppLanguage.EN to "Official procedures on Irembo & key agencies",
            AppLanguage.RW to "Ibisabwa kuri Irembo & Ibigo birisunzwe",
            AppLanguage.FR to "Procédures officielles sur Irembo & ministères"
        ),
        "cost_label" to mapOf(
            AppLanguage.EN to "Estimated Cost",
            AppLanguage.RW to "Ikiguzi",
            AppLanguage.FR to "Coût Estimé"
        ),
        "time_label" to mapOf(
            AppLanguage.EN to "Processing Time",
            AppLanguage.RW to "Igihe Bitwara",
            AppLanguage.FR to "Délai de Traitement"
        ),
        "requirements_label" to mapOf(
            AppLanguage.EN to "Key Requirements",
            AppLanguage.RW to "Ibisabwa by'Ingenzi",
            AppLanguage.FR to "Conditions Requises"
        ),
        "visit_portal" to mapOf(
            AppLanguage.EN to "Visit Application Portal",
            AppLanguage.RW to "Jya ku rubuga usabireho",
            AppLanguage.FR to "Aller sur le Portail Officiel"
        ),
        "emergency_desk" to mapOf(
            AppLanguage.EN to "Emergency Hotlines (Tap to Call)",
            AppLanguage.RW to "Inomero z'Ubutabazi (Kanda uhamagare)",
            AppLanguage.FR to "Lignes d'Urgence (Appui Tactile)"
        ),
        "alerts_subtitle" to mapOf(
            AppLanguage.EN to "Civic Broadcaster & Weather Alerts",
            AppLanguage.RW to "Amakuru yihuse & Imiterere y'Ibihe",
            AppLanguage.FR to "Alertes Civiques & Météo Locale"
        ),
        "opps_search_placeholder" to mapOf(
            AppLanguage.EN to "Search Jobs, Scholarships or NGO roles...",
            AppLanguage.RW to "Shakisha Imirimo cyangwa Amashuri...",
            AppLanguage.FR to "Rechercher des offres d'emploi, bourses..."
        ),
        "opps_deadline" to mapOf(
            AppLanguage.EN to "Deadline",
            AppLanguage.RW to "Termine ku wa",
            AppLanguage.FR to "Date Limite"
        ),
        "opps_apply" to mapOf(
            AppLanguage.EN to "Apply / Learn More",
            AppLanguage.RW to "Saba / Menya Ibindi",
            AppLanguage.FR to "Postuler / Voir Plus"
        ),
        "opps_bookmark" to mapOf(
            AppLanguage.EN to "Keep Offline",
            AppLanguage.RW to "Bika Udakoresheje Interineti",
            AppLanguage.FR to "Garder Hors Ligne"
        ),
        "opps_bookmarked" to mapOf(
            AppLanguage.EN to "Available Offline",
            AppLanguage.RW to "Emejwe Offline",
            AppLanguage.FR to "Disponible Hors Ligne"
        ),
        "offline_notice" to mapOf(
            AppLanguage.EN to "Offline State Enabled. All information is loaded locally.",
            AppLanguage.RW to "Iri gukora adafite internet. Amakuru yose yawe abitswe muri terefone.",
            AppLanguage.FR to "Mode Hors Ligne Activé. Toutes les données sont locales."
        ),
        "national_id" to mapOf(
            AppLanguage.EN to "National ID",
            AppLanguage.RW to "Indangamuntu",
            AppLanguage.FR to "Carte Nationale d'Identité"
        ),
        "passport" to mapOf(
            AppLanguage.EN to "E-Passport",
            AppLanguage.RW to "Pasiporo ya E",
            AppLanguage.FR to "Passeport Électronique"
        ),
        "driver_license" to mapOf(
            AppLanguage.EN to "Driver's License",
            AppLanguage.RW to "Uruhushya rwo Gutwara",
            AppLanguage.FR to "Permis de Conduire"
        )
    )

    data class GovService(
        val id: String,
        val titles: Map<AppLanguage, String>,
        val descriptions: Map<AppLanguage, String>,
        val costs: Map<AppLanguage, String>,
        val times: Map<AppLanguage, String>,
        val steps: Map<AppLanguage, List<String>>,
        val iremboUrl: String
    )

    val govServicesList = listOf(
        GovService(
            id = "national_id",
            titles = mapOf(
                AppLanguage.EN to "Application for National ID Card",
                AppLanguage.RW to "Gusaba Ikarita y'Indangamuntu",
                AppLanguage.FR to "Demande de Carte d'Identité Nationale"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "National Identification Card registration details for Rwandan Citizens above 16 years. Handled on IremboGov.",
                AppLanguage.RW to "Ibisobanuro ku byangombwa bisabwa ngo uhabwe Indangamuntu ku banyarwanda bageze ku myaka 16 cyangwa bayitaye.",
                AppLanguage.FR to "Enregistrement de la Carte Nationale d'Identité pour les citoyens rwandais dès 16 ans via IremboGov."
            ),
            costs = mapOf(
                AppLanguage.EN to "500 RWF (First time) / 1,500 RWF (Replacement)",
                AppLanguage.RW to "500 Frw (Ubwa mbere) / 1,500 Frw (Gusimbuza)",
                AppLanguage.FR to "500 RWF (Première fois) / 1 500 RWF (Remplacement)"
            ),
            times = mapOf(
                AppLanguage.EN to "30 Working Days",
                AppLanguage.RW to "Iminsi 30 y'Akazi",
                AppLanguage.FR to "30 Jours Ouvrables"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Create or log in to your account on IremboGov.",
                    "Select 'Application for National Identification Card'.",
                    "Fill in citizenship registration number or birth certificate ID.",
                    "Choose preferred sector of collection (where you will pick it up).",
                    "Pay via Mobile Money (MTN, Airtel), Bank, or BK Agent.",
                    "Go to the sector office for biometric capture if notified."
                ),
                AppLanguage.RW to listOf(
                    "Kora cyangwa winjire muri konti yawe kuri IremboGov.",
                    "Hitamo 'Gusaba ikarita nshya y'Indangamuntu'.",
                    "Uzuza nimero y'iyandikisha ku bwenegihugu cyangwa icyemezo cy'amavuko.",
                    "Hitamo Umurenge wifuza kuzayifatiramo.",
                    "Ishyura ukoresheje Mobile Money (MTN, Airtel) cyangwa banki.",
                    "Uzagana ku biro by'Umurenge kwifotoza nimero z'amavidewo biba ngombwa."
                ),
                AppLanguage.FR to listOf(
                    "Créez ou connectez-vous à votre compte sur IremboGov.",
                    "Sélectionnez 'Demande de carte d'identité nationale'.",
                    "Entrez le numéro d'enregistrement civique ou l'acte de naissance.",
                    "Choisissez le secteur de retrait de votre convenance.",
                    "Payez via Mobile Money (MTN, Airtel), virement bancaire ou BK Agent.",
                    "Rendez-vous au guichet du secteur pour la capture biométrique si requis."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        ),
        GovService(
            id = "e_passport",
            titles = mapOf(
                AppLanguage.EN to "East African Community Electronic Passport",
                AppLanguage.RW to "Pasiporo ya EAC yo mu buryo bw'Ikoranabuhanga",
                AppLanguage.FR to "Passeport Électronique de la CAE"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Application process for electronic passports for East African Community (EAC) citizens. Managed by the Directorate General of Immigration and Emigration (DGIE) via IremboGov.",
                AppLanguage.RW to "Uburyo bwo gusaba pasiporo igenewe abanyarwanda n'abaturage ba EAC. Bikurikiranwa n'Urwego rw'Abinjira n'Abasohoka binyuze kuri Irembo.",
                AppLanguage.FR to "Processus de demande de passeport électronique pour les citoyens de la CAE, géré par l'Immigration (DGIE) via IremboGov."
            ),
            costs = mapOf(
                AppLanguage.EN to "75,000 RWF (Ordinary - 5 Years) / 100,000 RWF (Ordinary - 10 Years)",
                AppLanguage.RW to "75,000 Frw (Isanzwe - Imyaka 5) / 100,000 Frw (Isanzwe - Imyaka 10)",
                AppLanguage.FR to "75 000 RWF (Ordinaire - 5 ans) / 100 000 RWF (Ordinaire - 10 ans)"
            ),
            times = mapOf(
                AppLanguage.EN to "4 Working Days (Expedited options available)",
                AppLanguage.RW to "Iminsi 4 y'Akazi (Hari n'izihuta cyane)",
                AppLanguage.FR to "4 Jours Ouvrables (Options express disponibles)"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Navigate to DGIE services under IremboGov portal.",
                    "Choose Application for E-Passport.",
                    "Provide National ID Card Number.",
                    "Attach passport photograph, certificate of citizenship or parent documents for minors.",
                    "Pay via online options or Mobile Money.",
                    "Receive SMS invitation to book bio-metrics at DGIE HQ (Kacyiru, Kigali)."
                ),
                AppLanguage.RW to listOf(
                    "Jya kuri serivisi z'Urwego ry'Abinjira binyuze kuri IremboGov.",
                    "Hitamo 'Gusaba Pasiporo nshya'.",
                    "Uzuza nimero y'Ikarita y'Indangamuntu.",
                    "Ongeraho ifoto isanzwe, icyemezo cy'ubwenegihugu, cyangwa inyandiko z'ababyeyi.",
                    "Ishyura amafaranga asabwa.",
                    "Tegereza SMS ikubwira kujya kwifotoza muri DGIE Kacyiru cyangwa biro z'Uturere."
                ),
                AppLanguage.FR to listOf(
                    "Rendez-vous dans la section DGIE d'IremboGov.",
                    "Sélectionnez 'Demande de Passeport Électronique'.",
                    "Renseignez votre numéro de carte d'identité.",
                    "Joignez une photo d'identité récente et l'acte d'état civil.",
                    "Effectuez le paiement officiel par Mobile Money/Banque.",
                    "Présentez-vous aux bureaux de la DGIE à Kacyiru (Kigali) pour la biométrie après notification."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        ),
        GovService(
            id = "mutuelle",
            titles = mapOf(
                AppLanguage.EN to "Mutuelle de Santé (Community Health Insurance)",
                AppLanguage.RW to "Ubusanzure bwa Mutuelle de Santé",
                AppLanguage.FR to "Souscription à la Mutuelle de Santé"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Universal community-based health insurance payment which guarantees cheap access to medicine and clinics. Payments calculated by Ubudehe tier classification.",
                AppLanguage.RW to "Uburyo bwo kwishyura ubwisungane mu kwivuza bwa Mutuelle hakoreshejwe icyiciro cy'Ubudehe, bigufasha kwivuza hafi ya ku buntu.",
                AppLanguage.FR to "Paiement de la couverture santé universelle communautaire pour accéder aux soins à tarif réduit. Calculé selon la catégorie Ubudehe."
            ),
            costs = mapOf(
                AppLanguage.EN to "3,000 RWF / Year per head (Tier 2/3) / Fully subsidized (Tier 1)",
                AppLanguage.RW to "3,000 Frw ku mwaka ku muntu (Icyiciro 2/3) / Ubuntu (Icyiciro 1)",
                AppLanguage.FR to "3 000 RWF / An par personne (Cat. 2/3) / Gratuit (Catégorie 1)"
            ),
            times = mapOf(
                AppLanguage.EN to "Instant Activation",
                AppLanguage.RW to "Guhita Byakira ako kanya",
                AppLanguage.FR to "Activation Immédiate"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Check dynamic health status using shortcode *909#.",
                    "Navigate to 'Mutuelle de Sante' payments on Irembo or select via Momo (*182*3*2#).",
                    "Enter Household ID (Inomero y'Irugo).",
                    "Confirm the members being paid for and their respective amounts.",
                    "Execute Mobile Money payment.",
                    "Verify SMS validation code. Hospital database updates automatically."
                ),
                AppLanguage.RW to listOf(
                    "Banza urebe uko uhagaze muri Mutuelle ukoresheje *909#.",
                    "Kanda kuri 'Kwishyura Mutuelle' kuri Irembo cyangwa Momo (*182*3*2#).",
                    "Uzuza Inomero y'Irugo.",
                    "Emeza abagize umuryango wishyurira hamwe n'amafaranga asabwa.",
                    "Kora urusesha rwo kwishyura kuri Mobile Money.",
                    "Ibitaro byose n'amavuriro ya Leta bihita bibona ko wishyuye."
                ),
                AppLanguage.FR to listOf(
                    "Vérifiez votre statut d'adhésion en tapant le code *909#.",
                    "Accédez à la section 'Mutuelle de Santé' sur Irembo ou via Momo (*182*3*2#).",
                    "Saisissez l'identifiant du ménage (Inomero y'Irugo).",
                    "Cochez les membres de la famille concernés par le règlement.",
                    "Finalisez la transaction Mobile Money.",
                    "Rendez-vous dans n'importe quel dispensaire public, la base de données est mise à jour instantanément."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        ),
        GovService(
            id = "driving_license",
            titles = mapOf(
                AppLanguage.EN to "Definitive & Provisional Driving License",
                AppLanguage.RW to "Gusaba no Gukorera Uruhushya rwo Gutwara",
                AppLanguage.FR to "Permis de Conduire Définitif / Provisoire"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Booking for traffic tests, provisional computer tests, and application for the definitive driving physical permit. Handled by Rwanda National Police and DGIE.",
                AppLanguage.RW to "Uburyo bwo kwiyandikisha ku bizamini by'imihanda (provisional/definitive) no gusaba uruhushya nyirizina muri Polisi n'Abinjira n'Abasohoka.",
                AppLanguage.FR to "Inscription aux examens de conduite théoriques et pratiques de la Police Nationale, et retrait du permis définitif."
            ),
            costs = mapOf(
                AppLanguage.EN to "10,000 RWF (Provisional booking) / 50,000 RWF (Definitive license issuance)",
                AppLanguage.RW to "10,000 Frw (Kwiyandikisha) / 50,000 Frw (Gufata Ikarita)",
                AppLanguage.FR to "10 000 RWF (Examen provisoire) / 50 000 RWF (Émission du permis)"
            ),
            times = mapOf(
                AppLanguage.EN to "Test Booking: Instant / Permit Issuance: 14 Working Days",
                AppLanguage.RW to "Kwizera Itariki: Uko kanya / Kubona uruhushya: Iminsi 14",
                AppLanguage.FR to "Rendez-vous: Immédiat / Émission de la carte: 14 Jours"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Visit Police Services on IremboGov.",
                    "Select registration for provisional theory test or practical test.",
                    "Input National ID and choose slot site / month of testing.",
                    "Pay required exam fee.",
                    "After passing, apply for 'Definitive Driving License Issuance' on Irembo.",
                    "Collect biological cards at standard sector or Traffic HQ after SMS notification."
                ),
                AppLanguage.RW to listOf(
                    "Sura serivisi za Polisi kuri IremboGov.",
                    "Hitamo 'Gusaba gukorera uruhushya rw'agateganyo cyangwa rwa burundu'.",
                    "Shyiramo Indangamuntu, hitamo igihe n'aho ushaka gukorera.",
                    "Ishyura amafaranga asabwa.",
                    "Nyuma yo gutsinda igizami, saba 'Guhabwa Uruhushya rwa Burundu' kuri Irembo.",
                    "Inyuma y'iminsi 14 uzahita ubwirwa SMS yo kujya gufata uruhushya rwawe."
                ),
                AppLanguage.FR to listOf(
                    "Accédez aux Services de la Police sur le portail Irembo.",
                    "Sélectionnez l'inscription aux examens théoriques ou pratiques.",
                    "Indiquez votre numéro d'identité nationale et choisissez le centre et la session.",
                    "Réglez le prix des sessions.",
                    "Une fois l'examen réussi, demandez la délivrance de la carte définitive via le portail.",
                    "Récupérez votre permis au centre désigné après réception du SMS officiel."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        ),
        GovService(
            id = "birth_certificate",
            titles = mapOf(
                AppLanguage.EN to "Birth Certificate (Icyemezo cy'Amavuko)",
                AppLanguage.RW to "Icyemezo cy'Amavuko kuri Irembo",
                AppLanguage.FR to "Acte de Naissance Certifié"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Official certified Birth Certificate generated instantly by Civil Registration authorities for official administrative requirements.",
                AppLanguage.RW to "Icyemezo kigaragaza igihe n'aho umuntu yavukiye ubusanzwe gitangwa n'Irembo gikoreshwa mu bindi byangombwa.",
                AppLanguage.FR to "Copie certifiée de l'Acte de Naissance, générée automatiquement pour les procédures juridiques et administratives."
            ),
            costs = mapOf(
                AppLanguage.EN to "2,000 RWF",
                AppLanguage.RW to "2,000 Frw",
                AppLanguage.FR to "2 000 RWF"
            ),
            times = mapOf(
                AppLanguage.EN to "1 Working Day (Often instant and downloadable PDF)",
                AppLanguage.RW to "Umunsi 1 (Kenshi ubona PDF ako kanya ukayiporinta)",
                AppLanguage.FR to "1 Jour Ouvrable (Souvent instantané à télécharger en PDF)"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Log in to Irembo and navigate to Civil Registration.",
                    "Select 'Application for Birth Certificate'.",
                    "Input National ID number of holder or Child's Birth Registration details.",
                    "Verify the fields automatically retrieved from NIDA data database.",
                    "Proceed to make the payment of 2000 RWF.",
                    "Download signed civil certificate directly from your Irembo homepage dashboard."
                ),
                AppLanguage.RW to listOf(
                    "Winjire kuri Irembo, ujye kuri serivisi z'Akarere cyangwa irangamimerere.",
                    "Hitamo 'Gusaba icyemezo cy'amavuko'.",
                    "Uzuza nomero y'indangamuntu cyangwa iy'iyandikisha ku mavuko y'umwana.",
                    "Genzura ko amakuru NIDA izanye neza ahuye.",
                    "Ishyura 2,000 Frw binyuze mu buryo bwa Mobile.",
                    "Kurura (Download) icyemezo cya PDF cyashyizweho umukono wa elegitoroniki."
                ),
                AppLanguage.FR to listOf(
                    "Connectez-vous sur Irembo et allez sur 'Enregistrement Civil'.",
                    "Choisissez 'Demande d'Acte de Naissance'.",
                    "Saisissez le NIR du titulaire ou les références d'enregistrement de l'enfant.",
                    "Vérifiez l'état civil importé automatiquement de la base NIDA.",
                    "Réglez les frais administratifs (2 000 RWF).",
                    "Téléchargez votre acte de naissance muni d'un code QR directement en PDF."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        ),
        GovService(
            id = "land_services",
            titles = mapOf(
                AppLanguage.EN to "Land Title Transfer & Registration Services",
                AppLanguage.RW to "Ibyangombwa by'Ubutaka (Kwiha no Guhindura)",
                AppLanguage.FR to "Mutation et Enregistrement Foncier"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Request land titles, register subdivision, apply for ownership transfer (mutation), or pay annual land taxes to Rwanda Land Management and Use Authority (RLMUA).",
                AppLanguage.RW to "Gusaba icyangombwa cy'ubutaka cyatakaye, kwandika ihererekanyabubasha, cyangwa kwishyura imisoro y'ubutaka buri mwaka.",
                AppLanguage.FR to "Demande de titre foncier, mutation de propriété immobilière, ou paiement de l'impôt foncier annuel à l'office d'urbanisme."
            ),
            costs = mapOf(
                AppLanguage.EN to "Taxes vary / Transfer fee standard 30,000 RWF",
                AppLanguage.RW to "Imisoro iratandukana / Guhindura ubugirane: 30,000 Frw",
                AppLanguage.FR to "Impôts selon valeur / Mutation forfaitaire 30 000 RWF"
            ),
            times = mapOf(
                AppLanguage.EN to "14 to 30 Working Days",
                AppLanguage.RW to "Iminsi 14 kugeza kuri 30 y'Akazi",
                AppLanguage.FR to "14 à 30 Jours Ouvrables"
            ),
            steps = mapOf(
                AppLanguage.EN to listOf(
                    "Obtain Unique Land Parcel Identifier (UPI) from local land registries.",
                    "Select Land Services section on IremboGov.",
                    "Input the UPI and select sub-service: Transfer, Replacement, or Division.",
                    "Enter details of the buyer and seller.",
                    "Pay application fee (usually standard 30,000 RWF).",
                    "Meet the sector Land Officer with witnesses for physical validation."
                ),
                AppLanguage.RW to listOf(
                    "Shaka UPI y'Ubutaka bwawe mu bitabo by'Akarere cyangwa umubazi.",
                    "Hitamo serivisi z'Ubutaka kuri Irembo portal.",
                    "ShyiramoUPI hanyuma uhitemo kwiha icyangombwa, gusimbuza, cyongera kubugabanyamo ibice.",
                    "Uzuza amazina y'Uwabuguzije n'Uwabuguze.",
                    "Ishyura amafaranga anyuzwa kuri terefone.",
                    "Ugasanga umukozi ushinzwe ubutaka ku murenge mufite abahamya bandi barebe."
                ),
                AppLanguage.FR to listOf(
                    "Munissez-vous du code d'identification foncière UPI de votre terrain.",
                    "Sélectionnez 'Services Fonciers' sur le portail IremboGov.",
                    "Renseignez l'UPI et choisissez le service (Mutation, Duplicata, Division).",
                    "Indiquez les identités de l'acheteur et du vendeur.",
                    "Procédez au paiement de la procédure (habituellement 30 000 RWF).",
                    "Présentez-vous devant l'officier foncier du secteur muni des témoins pour la signature définitive."
                )
            ),
            iremboUrl = "https://irembo.gov.rw"
        )
    )

    data class EmergencyContact(
        val nameEn: String,
        val nameRw: String,
        val nameFr: String,
        val number: String,
        val icon: String
    )

    val emergencyContacts = listOf(
        EmergencyContact(
            nameEn = "Rwanda National Police (General)",
            nameRw = "Polisi y'u Rwanda (Muri Rusange)",
            nameFr = "Police Nationale (Général)",
            number = "112",
            icon = "local_police"
        ),
        EmergencyContact(
            nameEn = "Road Safety & Accidents Hotline",
            nameRw = "Umutekano wo mu Muhanda & Impanuka",
            nameFr = "Sécurité Routière & Accidents",
            number = "113",
            icon = "traffic"
        ),
        EmergencyContact(
            nameEn = "Fire & Rescue Department",
            nameRw = "Kuzimya Inkongi & Ubutabazi",
            nameFr = "Pompiers & Service de Secours",
            number = "111",
            icon = "local_fire_department"
        ),
        EmergencyContact(
            nameEn = "SAMU (Ambulance / Medical Emergencies)",
            nameRw = "Imbangukiragutabara (Ambulance SAMU)",
            nameFr = "SAMU (Urgences Médicales / Ambulance)",
            number = "912",
            icon = "medical_services"
        ),
        EmergencyContact(
            nameEn = "Gender-Based Violence / Child Abuse",
            nameRw = "Amakuru y'Ihohoterwa rishingiye ku gitsina",
            nameFr = "Violences Sexistes or Abus d'Enfants",
            number = "3512",
            icon = "support"
        ),
        EmergencyContact(
            nameEn = "Electricity & Water Faults Hotline (REG/WASAC)",
            nameRw = "Ubutabazi ku bura bw'Ingufu/Mazi (REG/WASAC)",
            nameFr = "Ligne de Panne d'Énergie et Eau",
            number = "3535",
            icon = "flash_on"
        )
    )

    data class CivicAlert(
        val timestamp: String,
        val titles: Map<AppLanguage, String>,
        val contents: Map<AppLanguage, String>,
        val type: String // "warning", "info", "success"
    )

    val civicAlerts = listOf(
        CivicAlert(
            timestamp = "Just Now",
            titles = mapOf(
                AppLanguage.EN to "Heavy Rain & Flood Warning: Northern Province",
                AppLanguage.RW to "Imvura nyinshi Cyane & Umwuzure: Intara y'Amajyaruguru",
                AppLanguage.FR to "Alerte Météo: Fortes Pluies et Crues dans le Nord"
            ),
            contents = mapOf(
                AppLanguage.EN to "Rwanda Meteorology Agency (Meteo Rwanda) warns of potential flooding in Musanze and Gicumbi. Residents are advised to avoid water channels.",
                AppLanguage.RW to "Ikigo gishinzwe imiterere y'ibihe kiraburira abaturage batuye mu cyaro cya Gicumbi na Musanze kubera umuyaga n'imvura nshya itemba mu misozi.",
                AppLanguage.FR to "Meteo Rwanda met en garde contre des inondations éclair à Musanze et Gicumbi. Les riverains doivent s'éloigner des ravines."
            ),
            type = "warning"
        ),
        CivicAlert(
            timestamp = "2 Hours Ago",
            titles = mapOf(
                AppLanguage.EN to "Kigali Road Closure Notice: KN 3 Rd",
                AppLanguage.RW to "Imihanda ifunzwe mu Mujyi: KN 3 Rd",
                AppLanguage.FR to "Fermeture de Route à Kigali: Rue KN 3"
            ),
            contents = mapOf(
                AppLanguage.EN to "City of Kigali announces KN 3 Road (Sopetrad - Rwandex corridor) will be partially closed from 9 PM to 4 AM for expansion work. Please use alternative diversions.",
                AppLanguage.RW to "Umujyi wa Kigali watangaje ko umuhanda KN 3 (Sopetrad - Rwandex) uzafungwa igice guhera saa tatu z'ijoro ku bw'imirimo yo kuwagura.",
                AppLanguage.FR to "La mairie de Kigali annonce la fermeture partielle de la KN 3 (corridor Sopetrad - Rwandex) de 21h à 4h pour réfection. Itinéraires bis matérialisés."
            ),
            type = "info"
        ),
        CivicAlert(
            timestamp = "Yesterday",
            titles = mapOf(
                AppLanguage.EN to "Umuganda Community Work Update",
                AppLanguage.RW to "Gahunda y'Umuganda rusange w'Ukwezi",
                AppLanguage.FR to "Prochain Travail Communautaire Umuganda"
            ),
            contents = mapOf(
                AppLanguage.EN to "The Ministry of Local Government announces this Saturday's Monthly Umuganda. Focus will be on cleaning water drainages ahead of rain, and tree planting.",
                AppLanguage.RW to "Minisiteri y'Ubutegetsi bw'Igihugu ihamagarira abanyarwanda bose kwitabira imirimo y'Umuganda rusange kuri uyu wa gatandatu ku bw'amazi atemba.",
                AppLanguage.FR to "Le ministère de l'administration locale confirme l'Umuganda mensuel ce samedi. Les travaux porteront sur le curage des canalisations et la plantation d'arbres."
            ),
            type = "success"
        )
    )

    data class Opportunity(
        val id: String,
        val titles: Map<AppLanguage, String>,
        val categories: Map<AppLanguage, String>, // "Job" or "Scholarship"
        val institutions: Map<AppLanguage, String>,
        val deadLines: Map<AppLanguage, String>,
        val descriptions: Map<AppLanguage, String>,
        val links: String
    )

    val sampleOpportunities = listOf(
        Opportunity(
            id = "opp_1",
            titles = mapOf(
                AppLanguage.EN to "Software Engineer (Full-Stack) - BK Tech House",
                AppLanguage.RW to "Rwiyemezamirimo wa Porogaramu (Full-Stack) - BK Tech House",
                AppLanguage.FR to "Ingénieur Logiciel (Full-Stack) - BK Tech House"
            ),
            categories = mapOf(
                AppLanguage.EN to "Job",
                AppLanguage.RW to "Akazi",
                AppLanguage.FR to "Emploi"
            ),
            institutions = mapOf(
                AppLanguage.EN to "BK Tech House (Bank of Kigali Group)",
                AppLanguage.RW to "BK Tech House (Itsinda rya Banki ya Kigali)",
                AppLanguage.FR to "BK Tech House (Groupe Banque de Kigali)"
            ),
            deadLines = mapOf(
                AppLanguage.EN to "July 12, 2026",
                AppLanguage.RW to "Nyakanga 12, 2026",
                AppLanguage.FR to "12 Juillet 2026"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Help build digital banking infrastructure for Rwanda's largest financial group. Require experience in Spring Boot, Kotlin, and React/Compose. Working at the BK HQ in Kigali.",
                AppLanguage.RW to "Fasha kubaka ibyuma n'ikoranabuhanga muri BK Group. Bisaba ubunararibonye muri Spring Boot, Kotlin na Compose. Birakorerwa kuri BK HQ mu Mujyi wa Kigali.",
                AppLanguage.FR to "Participez au développement de la banque numérique du leader financier rwandais. Expérience exigée en Spring Boot, Kotlin et React/Compose. Poste basé au siège de la BK à Kigali."
            ),
            links = "https://bk.rw/careers"
        ),
        Opportunity(
            id = "opp_2",
            titles = mapOf(
                AppLanguage.EN to "Mastercard Foundation Scholars Program",
                AppLanguage.RW to "Ishami ry'Abanyeshuri rya Mastercard Foundation",
                AppLanguage.FR to "Programme de Bourses d'Études de la Fondation Mastercard"
            ),
            categories = mapOf(
                AppLanguage.EN to "Scholarship",
                AppLanguage.RW to "Buruse y'Amashuri",
                AppLanguage.FR to "Bourse d'Études"
            ),
            institutions = mapOf(
                AppLanguage.EN to "Carnegie Mellon University Africa (CMU-Africa)",
                AppLanguage.RW to "Carnegie Mellon University Africa (Kigali Innovation City)",
                AppLanguage.FR to "Université Carnegie Mellon Afrique (Kigali Innovation City)"
            ),
            deadLines = mapOf(
                AppLanguage.EN to "August 1, 2026",
                AppLanguage.RW to "Kanama 1, 2026",
                AppLanguage.FR to "1er Août 2026"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Fully-funded Master's scholarships in Information Technology, Electrical & Computer Engineering, and Artificial Intelligence for outstanding African youth at CMU-Africa (Kigali).",
                AppLanguage.RW to "Amashuri ya buruse yuzuye ya Kaminuza mu kiciro cya gatatu cy'amashuri makuru (Master's) mu ikoranabuhanga kuri CMU-Africa muri Kigali Innovation City.",
                AppLanguage.FR to "Bourses d'études de Master entièrement financées en informatique, ingénierie et IA pour de brillants jeunes africains au campus de CMU-Afrique (Kigali)."
            ),
            links = "https://www.africa.engineering.cmu.edu/admissions/scholarships.html"
        ),
        Opportunity(
            id = "opp_3",
            titles = mapOf(
                AppLanguage.EN to "District Health Officer (6 open roles)",
                AppLanguage.RW to "Umukozi ushinzwe ubuzima ku Karere",
                AppLanguage.FR to "Agent de Santé de District (6 Postes)"
            ),
            categories = mapOf(
                AppLanguage.EN to "Job",
                AppLanguage.RW to "Akazi",
                AppLanguage.FR to "Emploi"
            ),
            institutions = mapOf(
                AppLanguage.EN to "Ministry of Health (MinaSante) - Rwanda",
                AppLanguage.RW to "Minisiteri y'Ubuzima (MinaSante) - Rwanda",
                AppLanguage.FR to "Ministère de la Santé (MinaSante) - Rwanda"
            ),
            deadLines = mapOf(
                AppLanguage.EN to "July 20, 2026",
                AppLanguage.RW to "Nyakanga 20, 2026",
                AppLanguage.FR to "20 Juillet 2026"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Oversee community health worker initiatives, immunization schedules, and health service delivery in Gatsibo, Rubavu, and Nyamagabe districts. Requires public health degree.",
                AppLanguage.RW to "Gukurikirana imirimo y'Abajyanama b'ubuzima n'inkingo z'abana mu Turere twa Gatsibo, Rubavu na Nyamagabe. Bisaba kuba ufite impamyabumenyi mu buzima bw'abaturage.",
                AppLanguage.FR to "Supervision des programmes de santé communautaire, vaccination de routine et soins de santé primaire dans les districts de Gatsibo, Rubavu et Nyamagabe. Diplôme en santé publique requis."
            ),
            links = "https://www.moh.gov.rw"
        ),
        Opportunity(
            id = "opp_4",
            titles = mapOf(
                AppLanguage.EN to "Rwanda-Hungary Bilateral State Scholarship",
                AppLanguage.RW to "Buruse y'Amashuri ya Leta ya Hongiriya n'u Rwanda",
                AppLanguage.FR to "Programme de Bourses d'État Bilatérales Hongrie-Rwanda"
            ),
            categories = mapOf(
                AppLanguage.EN to "Scholarship",
                AppLanguage.RW to "Buruse y'Amashuri",
                AppLanguage.FR to "Bourse d'Études"
            ),
            institutions = mapOf(
                AppLanguage.EN to "Higher Education Council (HEC) Rwanda",
                AppLanguage.RW to "Inama Nkuru y'Amashuri Makuru (HEC Rda)",
                AppLanguage.FR to "Conseil de l'Enseignement Supérieur (HEC Rwanda)"
            ),
            deadLines = mapOf(
                AppLanguage.EN to "September 5, 2026",
                AppLanguage.RW to "Gashyantare 5, 2026",
                AppLanguage.FR to "5 Septembre 2026"
            ),
            descriptions = mapOf(
                AppLanguage.EN to "Bilateral higher education program offering undergraduate, master, and doctoral scholarships in sciences, agriculture, and water management in Budapest and Debrecen.",
                AppLanguage.RW to "Amasezerano akubiyemo inkunga y'amashuri muri Hongiriya mu buhinzi n'ubumenyi mu mazi (Bachelor, Master, PhD) bitangwa na HEC ku banyarwanda bakiri bato.",
                AppLanguage.FR to "Bourses d'État de premier cycle, master et doctorat pour étudier en Hongrie dans les STEM, l'agriculture et le traitement de l'eau à Budapest ou Debrecen."
            ),
            links = "https://hec.gov.rw"
        )
    )
}
