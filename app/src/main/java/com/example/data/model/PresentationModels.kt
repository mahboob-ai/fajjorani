package com.example.data.model

data class PresentationSlide(
    val slideNumber: Int,
    val title: String,
    val subtitle: String,
    val badge: String,
    val keyPoints: List<String>,
    val visualType: SlideVisualType,
    val speakerNotes: String
)

enum class SlideVisualType {
    INTRO_BRANDING,
    PROBLEM_PILLARS,
    SOLUTION_OVERVIEW,
    WORKFLOW_PIPELINE,
    CNN_VS_GEMINI,
    LIVE_DEMO_LINKS,
    LIMITATIONS_FUTURE,
    SDG_IMPACT
}

object PresentationDeck {
    val slides = listOf(
        PresentationSlide(
            slideNumber = 1,
            title = "AgriLens AI",
            subtitle = "AI-Powered Plant Disease Detection & Agricultural Assistance Platform",
            badge = "Team BioVision • SDG 2: Zero Hunger",
            keyPoints = listOf(
                "Tagline: \"Let the Leaf Tell the Story.\"",
                "Designed for farmers to screen leaf symptoms early using a smartphone.",
                "Combines visual leaf assessment with bilingual (English & Hindi) AI agricultural guidance.",
                "Built with transparent boundaries between implemented AI screening and planned offline CNN models."
            ),
            visualType = SlideVisualType.INTRO_BRANDING,
            speakerNotes = "Good morning everyone. We are Team BioVision, presenting AgriLens AI under SDG 2: Zero Hunger. Our mission is simple: help farmers spot possible crop diseases early using just a smartphone photo and clear, accessible guidance."
        ),
        PresentationSlide(
            slideNumber = 2,
            title = "Problem Statement",
            subtitle = "Why Early Crop Disease Awareness Matters",
            badge = "Field Challenge",
            keyPoints = listOf(
                "Early foliar symptoms are subtle and easily confused with nutrient stress or weather damage.",
                "Trained agricultural specialists and Krishi Vigyan Kendra (KVK) labs are not always nearby.",
                "Delayed identification allows fungal, bacterial, and viral infections to spread across fields.",
                "Farmers need simple, reliable first-level screening and preventive guidance in plain language."
            ),
            visualType = SlideVisualType.PROBLEM_PILLARS,
            speakerNotes = "In rural farming communities, small leaf spots often go unnoticed or misdiagnosed until the crop is already damaged. Experts are hard to reach immediately. AgriLens AI bridges that gap by giving farmers accessible first-level awareness."
        ),
        PresentationSlide(
            slideNumber = 3,
            title = "Proposed Solution",
            subtitle = "Accessible Smartphone Screening + Agricultural Guidance",
            badge = "Core Platform",
            keyPoints = listOf(
                "Smartphone Leaf Capture: Upload from gallery or capture via mobile camera across 42 crops.",
                "Multimodal AI Screening: Examines visible symptoms, possible causes, and safe prevention steps.",
                "AgriLens AI Crop Expert: Interactive Q&A assistant supporting simple English and Hindi.",
                "Crop Library & Local History: Educational guides and on-device record saving without mandatory login."
            ),
            visualType = SlideVisualType.SOLUTION_OVERVIEW,
            speakerNotes = "Our solution brings together four practical tools: a 42-crop leaf screening workflow, an interactive AI Crop Expert that supports English and Hindi, a searchable educational Crop Library, and local diagnosis history."
        ),
        PresentationSlide(
            slideNumber = 4,
            title = "System Workflow",
            subtitle = "End-to-End Architecture: From Leaf Photo to Actionable Guidance",
            badge = "6-Stage Pipeline",
            keyPoints = listOf(
                "1. Capture Image -> 2. Pre-processing (format, size validation & resizing).",
                "3. Feature Extraction -> 4. AI Analysis -> 5. Disease Classification.",
                "6. Guidance: Symptoms, causes, prevention tips, and KVK verification advice.",
                "Currently powered by Gemini multimodal screening; stages 3–5 prepare for planned CNN integration."
            ),
            visualType = SlideVisualType.WORKFLOW_PIPELINE,
            speakerNotes = "This slide shows our six-stage pipeline. Currently, image capture, validation, Gemini-assisted symptom screening, and guidance are live. In the next phase, dedicated OpenCV pre-processing and a trained CNN classifier will slot into stages 3 to 5."
        ),
        PresentationSlide(
            slideNumber = 5,
            title = "AI & CNN Technology",
            subtitle = "Implemented AI Screening vs. Planned Deep Learning Architecture",
            badge = "Technical Honesty",
            keyPoints = listOf(
                "Implemented Now: Gemini multimodal image understanding via secure Firebase AI Logic (no exposed keys).",
                "What a CNN Does: Convolutional Neural Networks learn edge, texture, and lesion patterns from labeled leaf datasets.",
                "Planned Stack: Python, OpenCV, TensorFlow/PyTorch training -> TensorFlow Lite (.tflite) conversion.",
                "Strict Transparency: We never fabricate confidence scores or pretend a trained CNN is already deployed."
            ),
            visualType = SlideVisualType.CNN_VS_GEMINI,
            speakerNotes = "An important highlight of our project is technical honesty. Today, AgriLens AI uses Gemini's multimodal understanding for preliminary symptom assessment. Training a dedicated CNN on labeled datasets and converting it to TensorFlow Lite for offline edge inference is clearly scoped as planned future work."
        ),
        PresentationSlide(
            slideNumber = 6,
            title = "Live Application Demonstration",
            subtitle = "Interactive Walkthrough of Working Modules",
            badge = "Live Demo",
            keyPoints = listOf(
                "Crop Disease Diagnosis: Select crop category (42 crops), upload/capture leaf photo, run live AI assessment.",
                "AI Crop Expert: Ask questions in English or Hindi, attach leaf images, use suggested chips.",
                "Crop Library & My History: Browse 5 crop categories and inspect locally persisted Room history.",
                "Tap the live demo buttons below to jump straight into Diagnose or AI Crop Expert!"
            ),
            visualType = SlideVisualType.LIVE_DEMO_LINKS,
            speakerNotes = "At this point in the presentation, we can demonstrate the live app directly—selecting a crop like Tomato or Rice, analyzing a leaf photo, asking the AI Crop Expert a question in English or Hindi, and showing how history persists locally."
        ),
        PresentationSlide(
            slideNumber = 7,
            title = "Limitations & Future Scope",
            subtitle = "Current Boundaries and Roadmap to Edge AI",
            badge = "Roadmap",
            keyPoints = listOf(
                "Limitations: Photo lighting/blur affects results; AI is preliminary and needs expert confirmation.",
                "Current Dependency: Live AI screening and expert chat require internet connectivity.",
                "Future Edge AI: Train custom CNN on regional crop datasets and deploy via TensorFlow Lite offline.",
                "Next Enhancements: Regional voice assistance, crop health tracking, and local KVK expert linkage."
            ),
            visualType = SlideVisualType.LIMITATIONS_FUTURE,
            speakerNotes = "Every responsible AI system acknowledges its limits. Blur or poor lighting can cause inconclusive results, and offline CNN inference is not yet implemented. Our roadmap focuses on TensorFlow Lite edge models, voice support in regional languages, and broader crop datasets."
        ),
        PresentationSlide(
            slideNumber = 8,
            title = "SDG Impact & Conclusion",
            subtitle = "Supporting Farmers & Sustainable Agriculture",
            badge = "SDG 2 • SDG 8 • SDG 12",
            keyPoints = listOf(
                "SDG 2 (Zero Hunger): Early awareness helps reduce preventable crop losses and protect food supply.",
                "SDG 8 (Decent Work & Economic Growth): Safeguards smallholder farmer income from sudden outbreaks.",
                "SDG 12 (Responsible Consumption & Production): Encourages targeted prevention over blind chemical overuse.",
                "Thank you! Questions & Feedback — Team BioVision."
            ),
            visualType = SlideVisualType.SDG_IMPACT,
            speakerNotes = "To conclude, early disease awareness supports SDG 2 Zero Hunger by protecting harvests, SDG 8 by safeguarding farmer livelihoods, and SDG 12 by promoting preventive care instead of unnecessary chemical spraying. Thank you for listening—we welcome your questions."
        )
    )
}
