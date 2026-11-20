package com.example.data.model

internal val cerealPulseAndCashCrops: List<CropInfo> = listOf(
    // C. CEREALS (5)
    CropInfo(
        id = "rice",
        name = "Rice",
        category = CropCategory.CEREALS,
        scientificName = "Oryza sativa",
        suitableGrowingConditions = "Clayey or loamy water-retentive soils (pH 5.5–7.0), warm humid growing season (22–32°C).",
        commonVisibleSymptoms = listOf(
            "Spindle-shaped (diamond) lesions with gray centers and brown borders on leaves",
            "Oval dark brown spots scattered across leaf blades",
            "Yellow-to-straw wavy blight stripes moving inward from leaf tips/margins",
            "Oval greenish-gray lesions on leaf sheaths just above waterline"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Rice Blast", "Fungal", "Forms characteristic diamond-shaped lesions on leaves and neck rot."),
            CropDiseaseInfo("Brown Spot", "Fungal", "Common in nutrient-stressed or potassium-deficient fields."),
            CropDiseaseInfo("Bacterial Leaf Blight", "Bacterial", "Creates wavy yellow-white streaks along leaf edges after storms.")
        ),
        generalPreventionMethods = listOf(
            "Avoid excessive nitrogen application, which makes leaves soft and blast-prone",
            "Use resistant local varieties and clean healthy seed",
            "Maintain balanced potassium and silicon nutrition and proper spacing"
        ),
        cropCareTips = listOf(
            "Drain standing water temporarily if bacterial blight or sheath blight surges",
            "Keep bunds clean of grassy weed hosts",
            "Scout nurseries and tillering stage leaves weekly"
        )
    ),
    CropInfo(
        id = "wheat",
        name = "Wheat",
        category = CropCategory.CEREALS,
        scientificName = "Triticum aestivum",
        suitableGrowingConditions = "Well-drained loamy or clay loam soil (pH 6.0–7.5) with cool vegetative winters (12–22°C) and dry sunny ripening.",
        commonVisibleSymptoms = listOf(
            "Bright yellow-orange powdery stripes arranged in neat parallel rows along veins",
            "Scattered orange-brown round rust pustules on upper leaf surfaces",
            "Irregular tan-brown blighted patches coalescing on lower leaves",
            "Blackened ears filled with dark powdery smut spores"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Yellow (Stripe) Rust", "Fungal", "Forms distinct yellow stripes; spreads rapidly in cool damp mornings."),
            CropDiseaseInfo("Leaf (Brown) Rust", "Fungal", "Reduces flag-leaf photosynthesis during grain filling."),
            CropDiseaseInfo("Spot Blotch / Leaf Blight", "Fungal", "Causes dark brown blotches in warmer humid zones.")
        ),
        generalPreventionMethods = listOf(
            "Sow rust-resistant wheat varieties recommended for your agro-zone",
            "Stick to timely sowing windows to escape peak late-season rust",
            "Use clean certified seed to prevent loose smut carryover"
        ),
        cropCareTips = listOf(
            "Inspect the top flag leaf closely since it contributes most to grain fill",
            "Avoid over-irrigation during cloudy cool weather",
            "Contact local agriculture extension immediately if stripe rust appears early"
        )
    ),
    CropInfo(
        id = "maize",
        name = "Maize",
        category = CropCategory.CEREALS,
        scientificName = "Zea mays",
        suitableGrowingConditions = "Deep fertile well-drained loamy soil (pH 5.8–7.2), full sunlight, warm temperatures (21–30°C).",
        commonVisibleSymptoms = listOf(
            "Long cigar-shaped gray-green to tan lesions parallel to leaf margins",
            "Small tan elliptical spots with dark borders across lower leaves",
            "Narrow chlorotic streaks turning purplish or stunted bushy growth",
            "Water-soaked soft rotting near lower stalk nodes"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Northern Corn Leaf Blight", "Fungal", "Creates distinctive long cigar-shaped gray-tan lesions."),
            CropDiseaseInfo("Maydis Leaf Blight", "Fungal", "Produces smaller tan elongated lesions on lower foliage."),
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Causes pale yellow streaking from leaf base outward.")
        ),
        generalPreventionMethods = listOf(
            "Ensure good field drainage since maize roots dislike waterlogging",
            "Rotate maize with pulses or oilseeds to break residue-borne fungi",
            "Select disease-tolerant hybrids suited to local rainfall"
        ),
        cropCareTips = listOf(
            "Keep fields weed-free during the first 4–5 weeks of growth",
            "Ensure adequate zinc and nitrogen split applications",
            "Remove early downy mildew affected plants to avoid spore spread"
        )
    ),
    CropInfo(
        id = "sorghum",
        name = "Sorghum",
        category = CropCategory.CEREALS,
        scientificName = "Sorghum bicolor",
        suitableGrowingConditions = "Well-drained medium to heavy soils (pH 6.0–7.5), drought-resilient warm sunny climate (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Small reddish-purple or tan oval spots with dark margins on leaves",
            "Long reddish-brown streaks along midribs and leaf blades",
            "Fine white downy coating on undersides of pale striped young leaves",
            "Moldy pinkish-black discoloration on maturing grain heads"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Anthracnose", "Fungal", "Forms small circular spots with tan centers and red-purple borders."),
            CropDiseaseInfo("Leaf Blight", "Fungal", "Produces long reddish-purple necrotic streaks."),
            CropDiseaseInfo("Grain Mold", "Fungal complex", "Affects panicles caught in late unseasonal rain.")
        ),
        generalPreventionMethods = listOf(
            "Use clean seed and rotate away from sorghum for 1–2 seasons",
            "Avoid planting near Johnson grass weeds that harbor sorghum pathogens",
            "Choose tolerant varieties for humid monsoon regions"
        ),
        cropCareTips = listOf(
            "Time planting so grain maturity avoids peak heavy rains",
            "Clear crop residues cleanly after harvest",
            "Maintain recommended row spacing for sunlight and airflow"
        )
    ),
    CropInfo(
        id = "pearl_millet",
        name = "Pearl Millet",
        category = CropCategory.CEREALS,
        scientificName = "Pennisetum glaucum",
        suitableGrowingConditions = "Light sandy to loamy well-drained soils (pH 6.0–7.5), warm semi-arid climate (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Pale yellow streaks starting from leaf base with white downy underside",
            "Leafy green ear-like growths replacing normal seed heads (Green Ear)",
            "Small orange-brown rust pustules on mature leaves",
            "Honeydew-like sticky drops on florets turning into dark hard ergot bodies"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew (Green Ear)", "Oomycete", "Transforms panicles into twisted green leafy structures."),
            CropDiseaseInfo("Pearl Millet Rust", "Fungal", "Causes reddish-brown pustules on leaves late in season."),
            CropDiseaseInfo("Ergot", "Fungal", "Produces sticky pinkish exudate and toxic dark sclerotia on ears.")
        ),
        generalPreventionMethods = listOf(
            "Plant downy-mildew resistant hybrids",
            "Rogue out infected green-ear plants before spores form",
            "Remove ergot-contaminated seed via salt-water flotation before sowing"
        ),
        cropCareTips = listOf(
            "Do not save seed from fields affected by green ear or ergot",
            "Ensure thin even plant stands without overcrowding",
            "Practice deep summer plowing to bury surface pathogen structures"
        )
    ),

    // D. PULSES (6)
    CropInfo(
        id = "chickpea",
        name = "Chickpea",
        category = CropCategory.PULSES,
        scientificName = "Cicer arietinum",
        suitableGrowingConditions = "Well-drained sandy loam to clay loam (pH 6.0–8.0), cool dry winter growing season (15–25°C).",
        commonVisibleSymptoms = listOf(
            "Concentric dark brown ringed lesions on leaves, stems, and pods",
            "Sudden drooping and straw-colored drying of whole plants in patches",
            "Grayish fuzzy mold on flowers and tender stems in dense wet canopies",
            "Dark collar rot near the soil surface in young seedlings"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Ascochyta Blight", "Fungal", "Spreads rapidly in cool wet weather causing stem breakage."),
            CropDiseaseInfo("Fusarium Wilt", "Fungal", "Blocks root-to-stem xylem causing dark internal vascular streaks."),
            CropDiseaseInfo("Botrytis Gray Mold", "Fungal", "Blasts flowers and pods in overly dense humid crops.")
        ),
        generalPreventionMethods = listOf(
            "Plant wilt-resistant varieties and clean healthy seed",
            "Avoid excessive irrigation or waterlogging in heavy soils",
            "Rotate chickpea with cereals for 2–3 years"
        ),
        cropCareTips = listOf(
            "Maintain wide row spacing for air circulation",
            "Avoid planting too densely in fertile soils",
            "Inspect wilted plants by slicing stem base vertically for brown vascular lines"
        )
    ),
    CropInfo(
        id = "lentil",
        name = "Lentil",
        category = CropCategory.PULSES,
        scientificName = "Lens culinaris",
        suitableGrowingConditions = "Well-drained loamy soil (pH 6.0–7.5) and cool winter climate (15–24°C) without standing moisture.",
        commonVisibleSymptoms = listOf(
            "Orange-yellow rust pustules clustering on leaflets and stems",
            "Tan circular lesions with dark borders on lower foliage",
            "Yellowing and wilting of scattered plants at flowering",
            "Dark water-soaked stem rot near soil line"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Lentil Rust", "Fungal", "Forms yellowish-orange pustules that turn dark brown and dry leaves."),
            CropDiseaseInfo("Stemphylium Blight", "Fungal", "Causes rapid leaf drop in humid cloudy weather."),
            CropDiseaseInfo("Wilt / Root Rot", "Fungal", "Leads to stunted yellowing and root decay.")
        ),
        generalPreventionMethods = listOf(
            "Use rust-tolerant cultivars and timely winter sowing",
            "Avoid dense canopies and excessive irrigation",
            "Rotate away from pulses to reduce soil-borne root fungi"
        ),
        cropCareTips = listOf(
            "Ensure gentle field leveling so rainwater drains quickly",
            "Scout lower shaded leaves during cloudy January–February spells",
            "Store harvested seed dry and clean"
        )
    ),
    CropInfo(
        id = "pigeon_pea",
        name = "Pigeon Pea",
        category = CropCategory.PULSES,
        scientificName = "Cajanus cajan",
        suitableGrowingConditions = "Deep well-drained loamy soil (pH 6.0–7.5), warm climate (22–35°C), intolerant of prolonged waterlogging.",
        commonVisibleSymptoms = listOf(
            "Bushy stunted plants with pale green mosaic mottling and no flowers",
            "Gradual yellowing and wilting of leaves with dark purple band on stem base",
            "Water-soaked brown stem lesions near soil after heavy rains",
            "Small brown angular spots on mature lower leaves"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Sterility Mosaic Disease", "Viral (Mite vector)", "Leaves turn small and mosaic-patterned; plants stay sterile."),
            CropDiseaseInfo("Fusarium Wilt", "Fungal", "Causes progressive wilting around flowering and podding."),
            CropDiseaseInfo("Phytophthora Stem Blight", "Oomycete", "Girdles stems during monsoon waterlogging.")
        ),
        generalPreventionMethods = listOf(
            "Grow varieties resistant to both Fusarium Wilt and Sterility Mosaic",
            "Plant on ridges or raised beds in monsoon-prone areas",
            "Uproot volunteer pigeon pea plants before the new season to break mite carry-over"
        ),
        cropCareTips = listOf(
            "Intercrop with sorghum or maize to reduce soil-borne spread",
            "Keep field bunds drained during heavy rain spells",
            "Inspect young plants at 30–45 days for mosaic symptoms"
        )
    ),
    CropInfo(
        id = "green_gram",
        name = "Green Gram",
        category = CropCategory.PULSES,
        scientificName = "Vigna radiata",
        suitableGrowingConditions = "Well-drained loamy to sandy loam soil (pH 6.2–7.5) and warm sunny weather (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Bright yellow patches turning entire leaflets golden yellow",
            "White powdery coating across upper leaf surfaces",
            "Circular reddish-brown leaf spots with gray centers",
            "Crinkled puckered leaves with thickened veins"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Mungbean Yellow Mosaic", "Viral (Whitefly vector)", "Major yield reducer; turns leaves bright yellow and stunts pods."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Appears in cool dry evenings near pod maturation."),
            CropDiseaseInfo("Cercospora Leaf Spot", "Fungal", "Causes dark brown spotting and early defoliation.")
        ),
        generalPreventionMethods = listOf(
            "Choose Yellow Mosaic Virus resistant varieties",
            "Rogue out early yellow-mosaic seedlings promptly",
            "Avoid planting near older infected legume fields"
        ),
        cropCareTips = listOf(
            "Keep fields weed-free during the first 25 days",
            "Ensure balanced phosphorus for strong root nodulation",
            "Avoid water stress during flowering and pod filling"
        )
    ),
    CropInfo(
        id = "black_gram",
        name = "Black Gram",
        category = CropCategory.PULSES,
        scientificName = "Vigna mungo",
        suitableGrowingConditions = "Fertile well-drained clay loam or loam soil (pH 6.0–7.5) and warm humid climate (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Scattered bright yellow mosaic patches expanding across trifoliate leaves",
            "Severe upward leaf crinkling and rough puckering",
            "Dusty white powdery patches on upper leaves",
            "Brown circular leaf spots coalescing in humid weather"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Yellow Mosaic Disease", "Viral (Whitefly vector)", "Reduces pod set severely when infection starts early."),
            CropDiseaseInfo("Leaf Crinkle", "Viral", "Causes enlarged crinkled leaves and deformed floral buds."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Covers leaves in white film during cool dry nights.")
        ),
        generalPreventionMethods = listOf(
            "Use certified seed of resistant black gram cultivars",
            "Monitor whiteflies and aphids during the first month",
            "Remove infected crinkled plants early in small plots"
        ),
        cropCareTips = listOf(
            "Avoid standing water in fields during monsoon season",
            "Ensure good plant spacing for rapid morning dew drying",
            "Harvest pods timely to avoid shattering"
        )
    ),
    CropInfo(
        id = "kidney_bean",
        name = "Kidney Bean",
        category = CropCategory.PULSES,
        scientificName = "Phaseolus vulgaris",
        suitableGrowingConditions = "Well-drained loamy soil (pH 6.0–6.8) and mild temperatures (18–26°C) without extreme heat.",
        commonVisibleSymptoms = listOf(
            "Small reddish-brown rust pustules surrounded by yellow halos",
            "Angular dark spots delimited by leaf veins",
            "Water-soaked spots turning brown with wide lemon-yellow halos",
            "Dark sunken lesions on pods and leaf veins"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Common Bacterial Blight", "Bacterial", "Forms brown necrotic spots with bright yellow borders."),
            CropDiseaseInfo("Bean Rust", "Fungal", "Creates dusty cinnamon-brown pustules on leaf undersides."),
            CropDiseaseInfo("Anthracnose", "Fungal", "Causes dark red-brown vein lesions and sunken pod spots.")
        ),
        generalPreventionMethods = listOf(
            "Never work in bean rows when leaves are wet with rain or dew",
            "Always plant clean certified seed (anthracnose & blight are seed-borne)",
            "Rotate beans away from the same plot for 2–3 years"
        ),
        cropCareTips = listOf(
            "Use drip or furrow irrigation to keep foliage dry",
            "Remove heavily spotted old crop residue after harvest",
            "Provide gentle support for pole varieties"
        )
    ),

    // E. CASH AND OTHER CROPS (5)
    CropInfo(
        id = "cotton",
        name = "Cotton",
        category = CropCategory.CASH_CROPS,
        scientificName = "Gossypium hirsutum",
        suitableGrowingConditions = "Deep well-drained black cotton or alluvial loam soil (pH 6.5–8.0), long warm sunny season (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Dark angular vein-bounded water-soaked leaf spots (black arm symptom)",
            "Upward or downward leaf curling with thickened veins and cup-shaped enations",
            "Circular brown target spots with concentric rings on lower leaves",
            "Sudden wilting of branches or reddish leaf scorching (leaf reddening)"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Cotton Leaf Curl", "Viral (Whitefly vector)", "Thickens leaf veins and stunts fruiting branches."),
            CropDiseaseInfo("Bacterial Blight (Angular Leaf Spot)", "Bacterial", "Creates angular translucent spots turning dark black-brown."),
            CropDiseaseInfo("Alternaria Leaf Spot", "Fungal", "Causes concentric brown lesions and premature leaf shedding.")
        ),
        generalPreventionMethods = listOf(
            "Plant tolerant hybrids and maintain timely sowing window",
            "Avoid excess nitrogen which triggers lush growth and whitefly outbreaks",
            "Destroy stubbles and stalks after final picking"
        ),
        cropCareTips = listOf(
            "Differentiate nutritional magnesium/potassium leaf reddening from disease",
            "Ensure good drainage during heavy monsoon storms",
            "Monitor whiteflies and jassids regularly using yellow sticky cards"
        )
    ),
    CropInfo(
        id = "sugarcane",
        name = "Sugarcane",
        category = CropCategory.CASH_CROPS,
        scientificName = "Saccharum officinarum",
        suitableGrowingConditions = "Deep fertile well-drained loamy soil (pH 6.5–7.5), tropical/subtropical warmth (25–35°C), abundant sunlight.",
        commonVisibleSymptoms = listOf(
            "Yellowing and drying of top spindle leaves with red internal stalk streaks",
            "Long black whip-like shoot emerging from the central growing point",
            "Yellowing along the midrib spreading into the leaf blade (Yellow Leaf)",
            "Small elongated orange-brown rust pustules on leaves"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Red Rot", "Fungal", "Most serious cane disease; causes alcohol-like odor and white cross-bands inside red stalk pith."),
            CropDiseaseInfo("Smut", "Fungal", "Produces signature black spore-filled whip from the shoot tip."),
            CropDiseaseInfo("Yellow Leaf Disease", "Viral", "Yellows the midrib from base upward, lowering juice sugar.")
        ),
        generalPreventionMethods = listOf(
            "Select healthy seed setts strictly from disease-free nursery plots",
            "Rogue out and burn smut whips before spore membranes rupture",
            "Avoid ratooning fields showing red rot symptoms"
        ),
        cropCareTips = listOf(
            "Ensure proper field drainage and earthing-up",
            "Rotate severely red-rot affected fields with rice or legumes",
            "Avoid using irrigation water flowing through infected neighboring fields"
        )
    ),
    CropInfo(
        id = "mustard",
        name = "Mustard",
        category = CropCategory.CASH_CROPS,
        scientificName = "Brassica juncea",
        suitableGrowingConditions = "Well-drained sandy loam to clay loam soil (pH 6.0–7.5) and cool dry winter season (15–25°C).",
        commonVisibleSymptoms = listOf(
            "Dark brown-to-black target-like concentric spots on lower leaves and pods",
            "Raised chalky white blister-like pustules on leaf undersides and deformed stems",
            "White powdery film on leaves and siliquae late in season",
            "Soft dark stem rot with cottony white fungal growth near base"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Alternaria Blight", "Fungal", "Forms dark concentric rings on leaves and shriveled spots on pods."),
            CropDiseaseInfo("White Rust", "Oomycete", "Produces raised white blisters and swollen staghead flower deformities."),
            CropDiseaseInfo("Sclerotinia Stem Rot", "Fungal", "Causes stem bleaching, lodging, and premature ripening.")
        ),
        generalPreventionMethods = listOf(
            "Sow on recommended dates to avoid humid late-season blight overlap",
            "Use clean seed and rotate mustard away from crucifers",
            "Avoid over-irrigation during cloudy cool winter spells"
        ),
        cropCareTips = listOf(
            "Scout lower leaves at 40–50 days after sowing",
            "Keep rows spaced properly for sunlight penetration",
            "Control mustard aphids early to keep plants vigorous"
        )
    ),
    CropInfo(
        id = "groundnut",
        name = "Groundnut",
        category = CropCategory.CASH_CROPS,
        scientificName = "Arachis hypogaea",
        suitableGrowingConditions = "Loose friable sandy loam soil (pH 6.0–6.8) for easy peg penetration, warm sunny climate (25–32°C).",
        commonVisibleSymptoms = listOf(
            "Dark brown circular leaf spots surrounded by clear yellow halos (Early Tikka)",
            "Darker black spots with faint or no yellow halos on undersides (Late Tikka)",
            "Orange-brown powdery rust pustules on lower leaf surfaces",
            "Sudden wilting with shredded stem base and white fungal strands"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Tikka Leaf Spot (Early & Late)", "Fungal", "Causes severe leaf shedding and lowers pod filling."),
            CropDiseaseInfo("Groundnut Rust", "Fungal", "Often co-occurs with late tikka, drying foliage prematurely."),
            CropDiseaseInfo("Stem / Collar Rot", "Fungal", "Attacks seedlings and pegging plants in warm wet soil.")
        ),
        generalPreventionMethods = listOf(
            "Rotate groundnut with sorghum, pearl millet, or maize",
            "Use clean undamaged seed kernels and avoid deep sowing",
            "Remove crop residue cleanly after lifting pods"
        ),
        cropCareTips = listOf(
            "Ensure adequate calcium (gypsum) at pegging stage for well-filled pods",
            "Avoid waterlogging at any growth phase",
            "Dry harvested pods quickly to prevent mold contamination"
        )
    ),
    CropInfo(
        id = "soybean",
        name = "Soybean",
        category = CropCategory.CASH_CROPS,
        scientificName = "Glycine max",
        suitableGrowingConditions = "Well-drained fertile loamy soil (pH 6.0–7.0), warm moist summer/monsoon season (22–30°C).",
        commonVisibleSymptoms = listOf(
            "Tiny tan-to-reddish-brown rust pustules clustered on lower leaf undersides",
            "Bright yellow mosaic mottling and downward leaf cupping",
            "Reddish-brown frog-eye circular leaf spots with light gray centers",
            "Dark brown stem blight and premature pod drying"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Soybean Rust", "Fungal", "Spreads rapidly via windborne spores causing premature defoliation."),
            CropDiseaseInfo("Yellow Mosaic Virus", "Viral (Whitefly vector)", "Turns foliage bright yellow and lowers pod count."),
            CropDiseaseInfo("Frogeye Leaf Spot", "Fungal", "Creates gray-centered eye-like lesions on upper leaves.")
        ),
        generalPreventionMethods = listOf(
            "Grow rust- and mosaic-tolerant soybean varieties",
            "Ensure proper row drainage during heavy monsoon rains",
            "Rotate soybean with maize, wheat, or sorghum"
        ),
        cropCareTips = listOf(
            "Scout the lower canopy leaves first for early rust pustules",
            "Avoid excessive nitrogen which suppresses natural nodulation",
            "Monitor whiteflies during early vegetative stages"
        )
    )
)
