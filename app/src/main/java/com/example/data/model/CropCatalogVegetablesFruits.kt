package com.example.data.model

internal val vegetableAndFruitCrops: List<CropInfo> = listOf(
    // A. VEGETABLES (16)
    CropInfo(
        id = "tomato",
        name = "Tomato",
        category = CropCategory.VEGETABLES,
        scientificName = "Solanum lycopersicum",
        suitableGrowingConditions = "Well-drained loamy soil (pH 6.0–6.8), warm sunlit days (20–28°C), and steady root-zone watering without soaking foliage.",
        commonVisibleSymptoms = listOf(
            "Concentric brown target-like rings on lower older leaves",
            "Yellowing leaf margins progressing upward",
            "Water-soaked dark patches with pale white underside mold",
            "Upward leaf curling or mosaic light-dark mottling"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Early Blight", "Fungal", "Causes dark concentric spots on older leaves and premature defoliation."),
            CropDiseaseInfo("Late Blight", "Oomycete", "Spreads rapidly in cool humid weather with dark water-soaked lesions."),
            CropDiseaseInfo("Tomato Leaf Curl", "Viral (Whitefly vector)", "Leads to stunted growth and tightly curled, thickened leaves.")
        ),
        generalPreventionMethods = listOf(
            "Avoid overhead irrigation; water near the root zone early in the day",
            "Maintain wide plant spacing and prune lower leaves touching soil",
            "Rotate tomatoes away from potato, brinjal, and chilli for 2–3 seasons",
            "Inspect leaf undersides weekly for whiteflies and aphids"
        ),
        cropCareTips = listOf(
            "Stake or trellis plants to keep foliage dry and improve airflow",
            "Apply organic mulch around stems to reduce soil splash onto lower leaves",
            "Remove severely infected leaves promptly and dispose away from the field"
        )
    ),
    CropInfo(
        id = "potato",
        name = "Potato",
        category = CropCategory.VEGETABLES,
        scientificName = "Solanum tuberosum",
        suitableGrowingConditions = "Loose, well-aerated sandy loam soil (pH 5.5–6.5) with cool nights (15–20°C) during tuber formation.",
        commonVisibleSymptoms = listOf(
            "Irregular dark brown or black blotches on leaf tips and margins",
            "Concentric necrotic rings on mature leaves",
            "White cottony growth on leaf undersides during humid mornings",
            "Sudden wilting and drying of upper shoots"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Late Blight", "Oomycete", "Produces rapid brown-black blighted patches in cool wet weather."),
            CropDiseaseInfo("Early Blight", "Fungal", "Forms dry, papery, ringed spots mostly on aging foliage."),
            CropDiseaseInfo("Brown Rot / Bacterial Wilt", "Bacterial", "Causes sudden leaf wilting even when soil moisture is adequate.")
        ),
        generalPreventionMethods = listOf(
            "Plant certified disease-free seed tubers",
            "Hill up soil cleanly around stems to protect developing tubers",
            "Avoid waterlogging and prolonged leaf wetness",
            "Practice 3-year crop rotation with cereals or legumes"
        ),
        cropCareTips = listOf(
            "Check weather forecasts during humid spells for early foliage inspection",
            "Keep field edges free of volunteer solanaceous weeds",
            "Ensure balanced potassium and phosphorus nutrition for stronger stems"
        )
    ),
    CropInfo(
        id = "brinjal",
        name = "Brinjal",
        category = CropCategory.VEGETABLES,
        scientificName = "Solanum melongena",
        suitableGrowingConditions = "Deep fertile loamy soil (pH 5.5–6.8) with full sun and warm temperatures (22–30°C).",
        commonVisibleSymptoms = listOf(
            "Small, crowded, pale-green leaves in cluster formation",
            "Round gray-brown leaf spots with dark borders",
            "Daytime drooping and yellowing of leaves on one side of the plant",
            "Circular sunken patches on developing fruits"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Little Leaf of Brinjal", "Phytoplasma", "Causes severe stunting and tiny narrow leaves transmitted by leafhoppers."),
            CropDiseaseInfo("Phomopsis Blight", "Fungal", "Creates circular gray-brown spots on leaves and fruit rot."),
            CropDiseaseInfo("Bacterial Wilt", "Bacterial", "Leads to rapid wilting of green foliage without initial yellowing.")
        ),
        generalPreventionMethods = listOf(
            "Use healthy nursery seedlings raised under insect-proof net protection",
            "Remove infected little-leaf plants early to curb vector spread",
            "Maintain good drainage to discourage soil-borne wilt pathogens"
        ),
        cropCareTips = listOf(
            "Use yellow sticky traps in small plots to monitor sucking pests",
            "Harvest mature fruits regularly to reduce plant stress",
            "Avoid transplanting into fields with recent wilt history"
        )
    ),
    CropInfo(
        id = "chilli",
        name = "Chilli",
        category = CropCategory.VEGETABLES,
        scientificName = "Capsicum annuum",
        suitableGrowingConditions = "Well-drained sandy loam to clay loam soil (pH 6.0–7.0), warm dry-to-moderate humidity (20–30°C).",
        commonVisibleSymptoms = listOf(
            "Upward or downward curling and crinkling of young leaves",
            "White powdery coating on lower and upper leaf surfaces",
            "Dark sunken lesions on ripe pods and small circular leaf spots",
            "Branch tip dieback from top shoots downward"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Chilli Leaf Curl", "Viral", "Transmitted by thrips and whiteflies; causes severe leaf puckering."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Appears as dusty white patches causing premature leaf drop."),
            CropDiseaseInfo("Anthracnose / Dieback", "Fungal", "Produces sunken black lesions on pods and shoot dieback.")
        ),
        generalPreventionMethods = listOf(
            "Select tolerant varieties and raise seedlings in protected nurseries",
            "Avoid excessive nitrogen fertilizer that attracts sap-sucking insects",
            "Keep rows weed-free to eliminate alternate viral hosts"
        ),
        cropCareTips = listOf(
            "Inspect young shoot tips for thrips and mites every few days",
            "Ensure steady moisture without water stagnation around the collar",
            "Pick off early anthracnose-affected pods before rain spreads spores"
        )
    ),
    CropInfo(
        id = "capsicum",
        name = "Capsicum",
        category = CropCategory.VEGETABLES,
        scientificName = "Capsicum annuum var. grossum",
        suitableGrowingConditions = "Rich well-drained loamy soil (pH 6.0–6.8), mild temperatures (18–26°C), and good air movement.",
        commonVisibleSymptoms = listOf(
            "Water-soaked spots turning brown with yellow halos",
            "Powdery white patches on older leaf undersides",
            "Mottled yellow-green mosaic patterns on young leaves",
            "Stem base darkening near soil line"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Bacterial Leaf Spot", "Bacterial", "Forms small dark water-soaked spots that coalesce and drop leaves."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Reduces photosynthesis and triggers early leaf shedding."),
            CropDiseaseInfo("Phytophthora Blight", "Oomycete", "Causes collar rot and sudden plant collapse in wet soil.")
        ),
        generalPreventionMethods = listOf(
            "Use raised beds for drainage during rainy periods",
            "Sanitize pruning tools and hands when handling plants",
            "Avoid working in the crop while leaves are wet with dew or rain"
        ),
        cropCareTips = listOf(
            "Provide gentle shade in peak summer heat to prevent sunscald",
            "Maintain consistent calcium and balanced moisture",
            "Space plants properly for quick drying of foliage"
        )
    ),
    CropInfo(
        id = "cabbage",
        name = "Cabbage",
        category = CropCategory.VEGETABLES,
        scientificName = "Brassica oleracea var. capitata",
        suitableGrowingConditions = "Fertile moisture-retentive loam (pH 6.0–7.0) and cool weather (15–22°C) for firm head formation.",
        commonVisibleSymptoms = listOf(
            "V-shaped yellow lesions starting from leaf edges with blackened veins",
            "Dark concentric leaf spots on outer wrapper leaves",
            "Yellowing and stunted growth with swollen clubbed roots",
            "Soft watery foul-smelling decay near the head base"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Black Rot", "Bacterial", "Creates signature V-shaped marginal yellowing with dark leaf veins."),
            CropDiseaseInfo("Alternaria Leaf Spot", "Fungal", "Forms circular dark brown spots on outer leaves."),
            CropDiseaseInfo("Clubroot", "Plasmodiophorid", "Distorts roots and causes daytime leaf wilting.")
        ),
        generalPreventionMethods = listOf(
            "Use clean disease-free transplants and rotate away from brassicas",
            "Maintain neutral soil pH around 6.5–7.0 to suppress clubroot",
            "Avoid overhead sprinkler watering late in the afternoon"
        ),
        cropCareTips = listOf(
            "Remove crop residue completely after harvest",
            "Monitor outer leaves early for chewing pests that open entry wounds",
            "Keep soil evenly moist to prevent head splitting"
        )
    ),
    CropInfo(
        id = "cauliflower",
        name = "Cauliflower",
        category = CropCategory.VEGETABLES,
        scientificName = "Brassica oleracea var. botrytis",
        suitableGrowingConditions = "Organic-rich, well-drained loam (pH 6.0–7.0) and cool growing conditions (15–20°C).",
        commonVisibleSymptoms = listOf(
            "Yellow angular blotches on upper leaves with purplish-gray downy underside",
            "V-shaped marginal chlorosis progressing along leaf veins",
            "Black or brown spotting on wrapper leaves and curd surface",
            "Hollow stem or curd browning from nutrient stress"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Affects seedlings and mature leaves in cool damp mornings."),
            CropDiseaseInfo("Black Rot", "Bacterial", "Spreads through water splash and contaminated tools."),
            CropDiseaseInfo("Alternaria Spot", "Fungal", "Can blemish curds in humid conditions.")
        ),
        generalPreventionMethods = listOf(
            "Ensure well-ventilated nursery beds to avoid damping-off and mildew",
            "Rotate crucifer crops on a 3-year cycle",
            "Provide adequate boron and molybdenum in deficient soils"
        ),
        cropCareTips = listOf(
            "Tie outer leaves gently over developing curds when needed to protect color",
            "Water at soil level and avoid water pooling around the crown",
            "Harvest curds promptly at peak compactness"
        )
    ),
    CropInfo(
        id = "cucumber",
        name = "Cucumber",
        category = CropCategory.VEGETABLES,
        scientificName = "Cucumis sativus",
        suitableGrowingConditions = "Light warm fertile soil (pH 6.0–6.8), abundant sunlight, and steady warmth (22–30°C).",
        commonVisibleSymptoms = listOf(
            "Angular yellow patches bounded by leaf veins with gray-purple underside fuzz",
            "White flour-like powdery spots on upper leaf surfaces",
            "Light and dark green mosaic mottling with distorted young leaves",
            "Sudden vine wilting starting from older runners"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Creates vein-limited yellow squares that turn brown and dry."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Thrives in warm dry days with high canopy humidity."),
            CropDiseaseInfo("Cucumber Mosaic", "Viral", "Mottles leaves and causes bumpy, stunted fruits.")
        ),
        generalPreventionMethods = listOf(
            "Grow vines on trellises to keep leaves dry and off the soil",
            "Select mildew-tolerant varieties where humidity is high",
            "Control aphids and cucumber beetles early"
        ),
        cropCareTips = listOf(
            "Water consistently at the base during flowering and fruit set",
            "Prune heavily shaded old basal leaves for better airflow",
            "Harvest cucumbers regularly to keep vines productive"
        )
    ),
    CropInfo(
        id = "onion",
        name = "Onion",
        category = CropCategory.VEGETABLES,
        scientificName = "Allium cepa",
        suitableGrowingConditions = "Friable sandy loam soil (pH 6.0–7.0), full sun, cool early growth followed by warm dry bulb ripening.",
        commonVisibleSymptoms = listOf(
            "Small sunken white spots turning purplish with yellow halos on tubular leaves",
            "Tip dieback and dry silvery streaks from leaf stress",
            "Pale green leaves turning yellow and collapsing near the neck",
            "Black powdery soot-like patches on outer bulb scales"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Purple Blotch", "Fungal", "Forms elongated purplish lesions that girdle leaves in humid weather."),
            CropDiseaseInfo("Stemphylium Blight", "Fungal", "Causes tan-to-brown leaf blighting starting from tips."),
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Leaves turn pale, develop violet-gray fuzz, and fold over.")
        ),
        generalPreventionMethods = listOf(
            "Plant on raised beds with good surface drainage",
            "Avoid excessive late-season nitrogen and frequent overhead watering",
            "Cure harvested bulbs thoroughly in shade before storage"
        ),
        cropCareTips = listOf(
            "Keep rows free of weeds since onion foliage offers little shade",
            "Inspect leaf sheaths for tiny thrips causing silvery scarring",
            "Stop irrigation once tops naturally begin to fall over"
        )
    ),
    CropInfo(
        id = "garlic",
        name = "Garlic",
        category = CropCategory.VEGETABLES,
        scientificName = "Allium sativum",
        suitableGrowingConditions = "Well-drained organic loam (pH 6.0–7.0) with cool growing period and dry warm harvest window.",
        commonVisibleSymptoms = listOf(
            "Orange-brown powdery pustules scattered along leaf blades",
            "Purple-bordered elongated spots on aging leaves",
            "Yellowing leaf tips progressing down to the neck",
            "White fluffy mycelium around the bulb base"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Garlic Rust", "Fungal", "Creates orange-red pustules that later turn black and dry leaves."),
            CropDiseaseInfo("Purple Blotch", "Fungal", "Weakens leaves during warm humid spells."),
            CropDiseaseInfo("White Rot", "Fungal", "Soil-borne pathogen causing basal decay and yellowing.")
        ),
        generalPreventionMethods = listOf(
            "Use clean, firm, disease-free seed cloves from healthy stock",
            "Avoid planting garlic in fields previously used for onion or garlic",
            "Ensure wide spacing and well-drained beds"
        ),
        cropCareTips = listOf(
            "Mulch lightly with clean straw to suppress weeds and conserve moisture",
            "Avoid bruising bulbs during lifting and dry cleanly before storing",
            "Remove any rotting cloves immediately before planting"
        )
    ),
    CropInfo(
        id = "okra",
        name = "Okra",
        category = CropCategory.VEGETABLES,
        scientificName = "Abelmoschus esculentus",
        suitableGrowingConditions = "Deep well-drained sandy loam to clay loam (pH 6.0–7.5) and warm sunny weather (25–35°C).",
        commonVisibleSymptoms = listOf(
            "Bright yellow network of cleared veins with green interveinal tissue",
            "White powdery coating on upper and lower leaf surfaces",
            "Angular dark spots on leaves causing premature leaf fall",
            "Stunted pods that become pale and tough"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Yellow Vein Mosaic", "Viral (Whitefly vector)", "Turns leaf veins strikingly yellow and drastically reduces pod quality."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Covers foliage in dusty white growth during dry-cool periods."),
            CropDiseaseInfo("Cercospora Leaf Spot", "Fungal", "Produces brown to black sooty spots on lower leaves.")
        ),
        generalPreventionMethods = listOf(
            "Plant Yellow Vein Mosaic resistant varieties",
            "Rogue out early mosaic-infected plants to limit whitefly transmission",
            "Rotate okra away from cotton and related malvaceous crops"
        ),
        cropCareTips = listOf(
            "Harvest tender pods every 2 days to encourage continuous flowering",
            "Avoid waterlogging during monsoon rains",
            "Encourage beneficial insects around field borders"
        )
    ),
    CropInfo(
        id = "pumpkin",
        name = "Pumpkin",
        category = CropCategory.VEGETABLES,
        scientificName = "Cucurbita moschata",
        suitableGrowingConditions = "Fertile well-drained soil (pH 6.0–7.0), wide spacing, and warm sunny conditions (22–32°C).",
        commonVisibleSymptoms = listOf(
            "Chalky white powdery patches expanding across broad leaves",
            "Yellow mosaic mottling and blistered distorted foliage",
            "Brown water-soaked stem lesions exuding gummy sap",
            "Angular pale yellow leaf spots during wet mornings"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Very common on broad pumpkin leaves; accelerates leaf senescence."),
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Causes rapid yellowing and browning of leaves in wet conditions."),
            CropDiseaseInfo("Gummy Stem Blight", "Fungal", "Produces stem cracks with amber exudate and leaf lesions.")
        ),
        generalPreventionMethods = listOf(
            "Provide generous vine spacing for morning dew evaporation",
            "Avoid overhead watering directly onto large leaf canopies",
            "Remove infected vines after harvest rather than tilling them in wet"
        ),
        cropCareTips = listOf(
            "Place dry straw under developing pumpkins to prevent soil rot",
            "Support pollination by conserving bees during morning hours",
            "Water deeply at the main root crown"
        )
    ),
    CropInfo(
        id = "bitter_gourd",
        name = "Bitter Gourd",
        category = CropCategory.VEGETABLES,
        scientificName = "Momordica charantia",
        suitableGrowingConditions = "Sandy loam rich in organic matter (pH 6.0–6.8), warm climate (24–32°C), and sturdy trellis support.",
        commonVisibleSymptoms = listOf(
            "Yellow angular spots on upper leaf surface with pale downy underside",
            "Powdery white specks on mature foliage",
            "Yellow-green mosaic mottling and leaf deformation",
            "Sudden vine wilt starting near the collar"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Spreads rapidly along trellis canopies during humid periods."),
            CropDiseaseInfo("Mosaic Virus", "Viral", "Mottles leaves and causes small malformed fruits."),
            CropDiseaseInfo("Fusarium Wilt", "Fungal", "Blocks water transport, causing yellowing and vine collapse.")
        ),
        generalPreventionMethods = listOf(
            "Train vines vertically on pandals or trellises to keep foliage dry",
            "Use clean seed and rotate out of cucurbit beds",
            "Remove lower yellowed leaves early to preserve airflow"
        ),
        cropCareTips = listOf(
            "Ensure well-drained soil mounds around each plant base",
            "Monitor undersides for fruit flies and sap-feeding aphids",
            "Pick fruits at market maturity for sustained flowering"
        )
    ),
    CropInfo(
        id = "bottle_gourd",
        name = "Bottle Gourd",
        category = CropCategory.VEGETABLES,
        scientificName = "Lagenaria siceraria",
        suitableGrowingConditions = "Deep well-drained loamy soil (pH 6.0–7.0), full sun, and warm temperatures (24–32°C).",
        commonVisibleSymptoms = listOf(
            "White dusty patches covering broad leaves and petioles",
            "Yellowish angular blotches turning brown and necrotic",
            "Brown target spots with yellow halos on older leaves",
            "Vascular browning and sudden wilting of runners"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Covers upper leaves and slows fruit growth if unchecked."),
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Causes rapid leaf blighting after rains or heavy dew."),
            CropDiseaseInfo("Anthracnose", "Fungal", "Produces brown leaf lesions and sunken fruit spots.")
        ),
        generalPreventionMethods = listOf(
            "Grow on overhead trellises to prevent soil contact and humidity buildup",
            "Avoid bruising fruits and stems during harvesting",
            "Rotate crops and clear plant debris post-harvest"
        ),
        cropCareTips = listOf(
            "Mulch root zones to keep soil moisture steady in summer heat",
            "Prune excessive unproductive lateral shoots",
            "Water early in the morning"
        )
    ),
    CropInfo(
        id = "spinach",
        name = "Spinach",
        category = CropCategory.VEGETABLES,
        scientificName = "Spinacia oleracea",
        suitableGrowingConditions = "Rich moist well-drained soil (pH 6.5–7.5) and cool weather (12–20°C) with gentle sunlight.",
        commonVisibleSymptoms = listOf(
            "Pale yellow patches on upper leaf blades with purple-gray underside mold",
            "Small tan circular spots with dark margins",
            "Stunted pale leaves from root stress or excess wetness",
            "Leaf crinkling and yellowing from mosaic infection"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew (Blue Mold)", "Oomycete", "Primary foliar disease in cool damp conditions."),
            CropDiseaseInfo("Cercospora Leaf Spot", "Fungal", "Creates circular tan spots making leaves unmarketable."),
            CropDiseaseInfo("Damping-Off", "Soil-borne fungi", "Collapses young seedlings in overly wet seedbeds.")
        ),
        generalPreventionMethods = listOf(
            "Sow seeds at proper spacing rather than overcrowded rows",
            "Water gently at soil level in the morning so leaves dry fast",
            "Use raised beds where drainage is slow"
        ),
        cropCareTips = listOf(
            "Harvest outer mature leaves cleanly to keep inner crown healthy",
            "Avoid excessive nitrogen right before warm spells",
            "Remove weeds early so air circulates freely at ground level"
        )
    ),
    CropInfo(
        id = "pea",
        name = "Pea",
        category = CropCategory.VEGETABLES,
        scientificName = "Pisum sativum",
        suitableGrowingConditions = "Well-drained loam (pH 6.0–7.5) and cool growing season (13–20°C) with good sunlight.",
        commonVisibleSymptoms = listOf(
            "White talcum-like powdery film across leaves, stems, and pods",
            "Orange-brown powdery pustules on leaf undersides",
            "Purplish-brown irregular spots on lower leaves and pods",
            "Yellowing lower leaves with blackened stem base"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Very common late in season; reduces pod fill and sweetness."),
            CropDiseaseInfo("Ascochyta Blight", "Fungal", "Causes dark spotted leaves and stem girdling in wet weather."),
            CropDiseaseInfo("Pea Rust", "Fungal", "Produces dusty brown-orange pustules on leaves.")
        ),
        generalPreventionMethods = listOf(
            "Choose powdery-mildew resistant pea varieties",
            "Use clean certified seed and avoid saving seed from spotted pods",
            "Rotate peas with cereals or non-legume vegetables"
        ),
        cropCareTips = listOf(
            "Provide simple trellis sticks or netting for climbing types",
            "Avoid over-watering once pods begin filling",
            "Pick pods regularly to keep plants vigorous"
        )
    ),

    // B. FRUITS (10)
    CropInfo(
        id = "mango",
        name = "Mango",
        category = CropCategory.FRUITS,
        scientificName = "Mangifera indica",
        suitableGrowingConditions = "Deep well-drained alluvial or loamy soil (pH 5.5–7.5), tropical/subtropical warmth, and dry weather during flowering.",
        commonVisibleSymptoms = listOf(
            "Dark irregular black spots and tip blight on young leaves and panicles",
            "White powdery coating on flower panicles and tender shoots",
            "Velvety dark sooty layer covering upper leaf surfaces",
            "Bunched, thickened, malformed shoot tips or flower clusters"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Anthracnose", "Fungal", "Causes leaf spots, blossom blight, and post-harvest fruit blackening."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Attacks panicles and young leaves, causing flower and fruit drop."),
            CropDiseaseInfo("Sooty Mold", "Fungal (on honeydew)", "Blocks sunlight on leaves following hopper or scale infestations.")
        ),
        generalPreventionMethods = listOf(
            "Prune crowded inner branches after harvest to open canopy sunlight",
            "Clear fallen infected leaves and mummified fruits under trees",
            "Monitor mango hoppers and mealybugs early before honeydew builds up"
        ),
        cropCareTips = listOf(
            "Avoid heavy irrigation during peak flowering unless moisture stress is severe",
            "Maintain clean orchard basins free of tall weeds",
            "Consult local horticulture extension before seasonal flowering protection"
        )
    ),
    CropInfo(
        id = "banana",
        name = "Banana",
        category = CropCategory.FRUITS,
        scientificName = "Musa acuminata / Musa paradisiaca",
        suitableGrowingConditions = "Deep rich loamy soil with high organic matter (pH 6.0–7.5), warm humid climate (25–32°C), and wind protection.",
        commonVisibleSymptoms = listOf(
            "Yellow streaks turning dark reddish-brown along leaf veins",
            "Older lower leaves yellowing from margins inward and collapsing around pseudostem",
            "Narrow upright bunched leaves with dark green streaks on petioles",
            "Splitting at the pseudostem base"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Sigatoka Leaf Spot (Yellow/Black)", "Fungal", "Destroys green leaf area needed for full bunch weight."),
            CropDiseaseInfo("Panama Wilt (Fusarium)", "Fungal", "Soil-borne wilt causing progressive collapse of outer leaves."),
            CropDiseaseInfo("Bunchy Top", "Viral (Aphid vector)", "Stunts the plant severely with rosette upright leaves.")
        ),
        generalPreventionMethods = listOf(
            "Plant healthy tissue-culture plantlets from certified nurseries",
            "De-leaf heavily spotted dead leaves carefully to lower spore load",
            "Ensure excellent drainage to avoid root suffocation and wilt spread"
        ),
        cropCareTips = listOf(
            "Maintain optimal sucker management (keep one follower sucker)",
            "Prop heavy bunches during windy months",
            "Mulch with clean organic matter to preserve steady soil moisture"
        )
    ),
    CropInfo(
        id = "apple",
        name = "Apple",
        category = CropCategory.FRUITS,
        scientificName = "Malus domestica",
        suitableGrowingConditions = "Well-drained loamy hill or temperate soil (pH 6.0–6.8) with sufficient winter chill hours and sunny summers.",
        commonVisibleSymptoms = listOf(
            "Olive-green velvety spots turning dark brown on leaves and fruit skin",
            "White powdery felt on new terminal shoots and folded leaves",
            "Scorched blackened shepherd's-crook shoot tips",
            "Early leaf yellowing and premature autumn drop"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Apple Scab", "Fungal", "Creates olive-black velvety lesions on leaves and corky fruit scabs."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Deforms young shoots and lowers bud vigor."),
            CropDiseaseInfo("Fire Blight", "Bacterial", "Gives shoots a fire-scorched appearance during warm spring rains.")
        ),
        generalPreventionMethods = listOf(
            "Prune dormant trees cleanly to allow air movement and sun penetration",
            "Collect and compost or bury fallen scabbed leaves after autumn drop",
            "Sanitize pruning shears between cuts if fire blight is suspected"
        ),
        cropCareTips = listOf(
            "Choose scab-tolerant rootstocks and cultivars for humid valleys",
            "Avoid excessive late-summer nitrogen fertilizing",
            "Inspect young spring foliage closely after prolonged spring rain"
        )
    ),
    CropInfo(
        id = "papaya",
        name = "Papaya",
        category = CropCategory.FRUITS,
        scientificName = "Carica papaya",
        suitableGrowingConditions = "Light porous well-drained soil (pH 6.0–6.8), frost-free warmth (22–32°C), zero tolerance for standing water.",
        commonVisibleSymptoms = listOf(
            "Mottled yellow-green mosaic leaves with dark green oily streaks on petioles",
            "Severe upward leaf curling and distorted shoestring leaflets",
            "Water-soaked stem base rotting near ground level",
            "White powdery patches on older lower leaves"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Papaya Ringspot Virus", "Viral (Aphid vector)", "Causes mosaic, leaf distortion, and concentric rings on fruits."),
            CropDiseaseInfo("Papaya Leaf Curl", "Viral", "Crinkles leaves downward/upward and stunts fruiting."),
            CropDiseaseInfo("Collar / Foot Rot", "Oomycete", "Kills trees quickly in poorly drained or waterlogged basins.")
        ),
        generalPreventionMethods = listOf(
            "Plant on raised mounds so irrigation water never touches the trunk",
            "Use border barrier crops like maize to reduce aphid landings",
            "Remove early virus-infected young plants to protect healthy neighbors"
        ),
        cropCareTips = listOf(
            "Never let irrigation water stagnate even for 24 hours",
            "Apply balanced nutrition to support continuous flowering",
            "Protect young seedlings with insect-proof netting prior to transplanting"
        )
    ),
    CropInfo(
        id = "grapes",
        name = "Grapes",
        category = CropCategory.FRUITS,
        scientificName = "Vitis vinifera",
        suitableGrowingConditions = "Deep well-drained sandy loam to gravelly soil (pH 6.5–7.5), sunny dry ripening season, and trained trellis canopy.",
        commonVisibleSymptoms = listOf(
            "Translucent oily yellow spots on upper leaves with white downy underside",
            "Grayish-white dusty coating on leaves, shoots, and young berries",
            "Dark sunken bird's-eye spots on berries and leaves",
            "Brown dry leaf margins from moisture or salinity stress"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Can defoliate vines rapidly during warm humid weather."),
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Causes dusty coat on berries leading to berry cracking."),
            CropDiseaseInfo("Anthracnose", "Fungal", "Forms sunken dark spots on young shoots and berries.")
        ),
        generalPreventionMethods = listOf(
            "Train vines and manage canopy shoots for airflow around fruit clusters",
            "Remove diseased pruned canes cleanly from the vineyard",
            "Use drip irrigation instead of wetting foliage"
        ),
        cropCareTips = listOf(
            "Follow timed leaf-pulling around clusters where appropriate",
            "Monitor weather humidity closely after pruning",
            "Keep vineyard floor clean of fallen berries"
        )
    ),
    CropInfo(
        id = "citrus",
        name = "Citrus",
        category = CropCategory.FRUITS,
        scientificName = "Citrus spp. (Orange, Lime, Lemon)",
        suitableGrowingConditions = "Deep well-drained loamy soil (pH 6.0–7.5), sunny climate, and steady micronutrient availability (zinc, iron).",
        commonVisibleSymptoms = listOf(
            "Raised corky tan lesions surrounded by bright yellow halos on leaves and fruits",
            "Blotchy asymmetric yellowing across leaf halves (greening symptom)",
            "Dark velvety sooty coating on upper leaves",
            "Twig dieback from branch tips downward"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Citrus Canker", "Bacterial", "Creates raised scabby lesions with yellow halos on leaves and fruit."),
            CropDiseaseInfo("Huanglongbing (Citrus Greening)", "Bacterial (Psyllid vector)", "Causes mottled yellowing, twig dieback, and bitter lopsided fruit."),
            CropDiseaseInfo("Gummosis / Foot Rot", "Oomycete", "Produces bark cracking and gum oozing near the trunk base.")
        ),
        generalPreventionMethods = listOf(
            "Source certified disease-free grafted nursery saplings",
            "Control citrus psyllids and leafminers that spread bacterial infections",
            "Keep graft union well above soil and irrigation water level"
        ),
        cropCareTips = listOf(
            "Correct zinc and iron deficiencies which mimic viral mottling",
            "Prune dead twigs regularly and disinfect tools",
            "Ensure basin rings keep water away from direct trunk contact"
        )
    ),
    CropInfo(
        id = "guava",
        name = "Guava",
        category = CropCategory.FRUITS,
        scientificName = "Psidium guajava",
        suitableGrowingConditions = "Adaptable to well-drained loamy soils (pH 5.5–7.5), tropical/subtropical sunshine, and moderate rainfall.",
        commonVisibleSymptoms = listOf(
            "Yellowing and browning of leaves on individual branches followed by sudden wilt",
            "Small dark brown sunken spots on leaves and ripening fruits",
            "Velvety orange-brown algal spots on upper leaf surfaces",
            "Corky raised scabs on young fruits"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Guava Wilt", "Fungal complex", "Causes progressive branch drying and eventual tree mortality."),
            CropDiseaseInfo("Anthracnose", "Fungal", "Creates dark sunken spots and fruit rot during rainy harvests."),
            CropDiseaseInfo("Algal Leaf Spot", "Algal", "Forms orange-rusty circular patches on older shaded leaves.")
        ),
        generalPreventionMethods = listOf(
            "Avoid deep root injury during hoeing around the tree canopy",
            "Ensure good drainage and avoid planting in wilt-history pits",
            "Prune overlapping branches for sunlight and airflow"
        ),
        cropCareTips = listOf(
            "Bag developing fruits where practical to protect against fruit flies and anthracnose",
            "Apply well-decomposed compost around the drip line",
            "Remove mummified fruits remaining on branches"
        )
    ),
    CropInfo(
        id = "pomegranate",
        name = "Pomegranate",
        category = CropCategory.FRUITS,
        scientificName = "Punica granatum",
        suitableGrowingConditions = "Deep loamy to medium soil (pH 6.5–7.5), hot dry summers, and controlled drip irrigation.",
        commonVisibleSymptoms = listOf(
            "Water-soaked black spots with yellow halos on leaves turning necrotic",
            "L- or Y-shaped dark cracks on ripening pomegranate fruit skin",
            "Circular brown leaf spots causing early defoliation",
            "Stem darkening at nodal lesions"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Bacterial Blight (Oily Spot)", "Bacterial", "Most destructive disease; causes oily black leaf/stem lesions and fruit cracking."),
            CropDiseaseInfo("Cercospora Fruit & Leaf Spot", "Fungal", "Causes dark brown blotches on foliage and rind."),
            CropDiseaseInfo("Wilt Complex", "Fungal / Nematode", "Leads to yellowing and sudden wilting of main stems.")
        ),
        generalPreventionMethods = listOf(
            "Maintain strict orchard sanitation and disinfect secateurs between trees",
            "Avoid monsoon-season crop regulation in blight-endemic areas",
            "Remove and destroy fallen blighted leaves and cracked fruits"
        ),
        cropCareTips = listOf(
            "Use balanced drip watering to prevent sudden fruit cracking",
            "Maintain 3–4 well-spaced main stems for open canopy light",
            "Apply clean orchard rest periods between harvests"
        )
    ),
    CropInfo(
        id = "watermelon",
        name = "Watermelon",
        category = CropCategory.FRUITS,
        scientificName = "Citrullus lanatus",
        suitableGrowingConditions = "Deep sandy loam soil (pH 6.0–6.8), abundant warm sunshine (25–35°C), and dry ripening weather.",
        commonVisibleSymptoms = listOf(
            "Dark brown target-ringed spots on older crown leaves",
            "Yellow angular blotches and rapid scorching of leaves in humidity",
            "One-sided runner wilting during warm midday hours",
            "Stem lesions near crown with gummy amber droplets"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Anthracnose", "Fungal", "Causes dark leaf lesions, stem cankers, and sunken fruit spots."),
            CropDiseaseInfo("Gummy Stem Blight", "Fungal", "Attacks crown stems and causes leaf blighting."),
            CropDiseaseInfo("Fusarium Wilt", "Fungal", "Soil-borne wilt causing irreversible vine collapse.")
        ),
        generalPreventionMethods = listOf(
            "Use long crop rotations (4+ years) away from watermelon",
            "Choose wilt-tolerant varieties and clean seed",
            "Avoid walking through wet vines to prevent mechanical spore spread"
        ),
        cropCareTips = listOf(
            "Use plastic or straw mulch to keep fruits clean and reduce soil splash",
            "Taper watering gently as fruits near full ripeness for better sweetness",
            "Monitor crown leaves early since foliar spots start near the center"
        )
    ),
    CropInfo(
        id = "muskmelon",
        name = "Muskmelon",
        category = CropCategory.FRUITS,
        scientificName = "Cucumis melo",
        suitableGrowingConditions = "Well-drained sandy loam (pH 6.0–7.0), dry sunny ripening climate (24–32°C), and careful moisture balance.",
        commonVisibleSymptoms = listOf(
            "Dusty white circular spots merging over upper leaf surfaces",
            "Angular yellow mosaic patches turning brown on older leaves",
            "Mottled light-dark green blisters on young vine tips",
            "Sudden afternoon wilting of mature runners"
        ),
        commonDiseases = listOf(
            CropDiseaseInfo("Powdery Mildew", "Fungal", "Reduces leaf area and lowers fruit sugar accumulation."),
            CropDiseaseInfo("Downy Mildew", "Oomycete", "Can rapidly scorch foliage after dewy nights."),
            CropDiseaseInfo("Mosaic Viruses", "Viral", "Transmitted by aphids/whiteflies; causes stunted netted fruit.")
        ),
        generalPreventionMethods = listOf(
            "Select powdery mildew resistant hybrids",
            "Control early sucking pests and keep field borders weed-free",
            "Water via furrows or drip rather than overhead sprinklers"
        ),
        cropCareTips = listOf(
            "Avoid excess nitrogen once fruit netting begins",
            "Rest developing melons on dry ground or straw pads",
            "Inspect leaf undersides regularly for early mildew colonies"
        )
    )
)
