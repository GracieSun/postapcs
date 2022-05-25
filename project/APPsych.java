import java.util.*;
public class APPsych {
    public static ArrayList<Questions> yayQuestions = new ArrayList<Questions>();
    public static ArrayList<Questions> questionsRemaining = new ArrayList<Questions>();
    public static ArrayList<Questions> wrongAns = new ArrayList<Questions>();
    public static ArrayList<Questions> quiz = new ArrayList<Questions>();
    public static ArrayList<String> quizAns = new ArrayList<String>();
    public static String correctAns = "";
    public static boolean firstTime = true;
    public void runQuiz() {
        yayQuestions.clear();
        questionsRemaining.clear();
        quiz.clear();
        if (firstTime) {
            System.out.println("This is a multiple choice AP Psych review quiz test game thing.");
            firstTime = false;
        }
        System.out.println("What would you like to study?");
        System.out.println("A: parts of brain");
        System.out.println("B: memory related stuff");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.next();
        if (type.toUpperCase().equals("A")) {
            allTheBrainPartTerms();
        }
        else if (type.toUpperCase().equals("B")) {
            allTheLearnAndMemTerms();
        }
        else {
            allTheTerms();
        }
        setEqual();
        int max = (yayQuestions.size()/10) * 10;
        int min = 1;
        //System.out.println("size " + yayQuestions.size());
        System.out.println("How many terms would you like to review?");
        System.out.println("Option from " + min + " to " + max);
        int numberOfQuestions = (int)scanner.nextInt();
        int correct = 0;
        if (numberOfQuestions > max) {
            numberOfQuestions = max;
        }
        else if (numberOfQuestions < min) {
            numberOfQuestions = min;
        }
        for (int i = 0; i < numberOfQuestions; i++) {
            int addTerm = (int)(Math.random()*questionsRemaining.size());
            quiz.add(questionsRemaining.get(addTerm));
            questionsRemaining.remove(addTerm);
        }
        //quiz generated
        long start = System.currentTimeMillis();
        for (int i = 0; i < quiz.size(); i++) {
            Questions thisQuestion = quiz.get(i);
            setEqual();
            makeAns(thisQuestion);
            System.out.println(thisQuestion.getTerm());
            System.out.println("A: " + quizAns.get(0));
            System.out.println("B: " + quizAns.get(1));
            System.out.println("C: " + quizAns.get(2));
            System.out.println("D: " + quizAns.get(3));
            String answer = scanner.next();
            if (answer.toUpperCase().equals("QUIT")) {
                return;
            }
            if (answer.toUpperCase().equals(correctAns)) {
                correct++;
            }
            else {
                wrongAns.add(thisQuestion);
            }
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        timeElapsed /= 1000.0;
        System.out.println("You got " + correct + " questions right");
        System.out.println("It took you " + (timeElapsed) + " seconds");
        double realTime = realTestTime(timeElapsed, numberOfQuestions);
        System.out.println("At that rate, the actual test will take you " + 
        realTime + " minutes");
        if (correct != numberOfQuestions) {
            reviewAnswers();
        }
        System.out.println("Would you like to study again");
        String review = scanner.next().toUpperCase();
        if (review.equals("YES") || review.equals("Y")) {
            runQuiz();
        }
    }
    public void setEqual() {
        questionsRemaining.clear();
        for (int i = 0; i < yayQuestions.size(); i++) {
            questionsRemaining.add(yayQuestions.get(i));
        }
    }
    public void makeAns(Questions avoid) {
        quizAns.clear();
        for (int i = 1; i <= 3; i++) {
            int addTerm = (int)(Math.random()*questionsRemaining.size());
            if (questionsRemaining.get(addTerm).getTerm().equals(avoid.getTerm())) {
                questionsRemaining.remove(addTerm);
                addTerm = (int)(Math.random()*questionsRemaining.size());
            }
            quizAns.add(questionsRemaining.get(addTerm).getDefinition());
            questionsRemaining.remove(addTerm);
        }
        int where = (int)(Math.random()*4);
        quizAns.add(where, avoid.getDefinition());
        if (where == 0) {
            correctAns = "A";
        }
        else if (where == 1) {
            correctAns = "B";
        }
        else if (where == 2) {
            correctAns = "C";
        }
        else if (where == 3) {
            correctAns = "D";
        }
    }
    public void reviewAnswers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("review incorrect answers?");
        String review = scanner.next();
        if (!review.toUpperCase().equals("YES") && !review.toUpperCase().equals("Y")) {
            System.out.println("well you're reviewing them anyways");
        }
        for (int i = 0; i < wrongAns.size(); i++) {
            System.out.println("Term: " + wrongAns.get(i).getTerm());
            System.out.println("Definition: " + wrongAns.get(i).getDefinition());
        }
    }
    public double realTestTime(double time, int numQues) {
        System.out.println("seconds per question " + (time/numQues));
        double realTime = 100 * (time/numQues); //seconds
        realTime /= 60; //now minutes
        return realTime;
    }
    public void allTheTerms() {
        allTheBrainPartTerms();
        allTheLearnAndMemTerms();
    }
    public void allTheLearnAndMemTerms() {
        Questions cogMap = new Questions("cognitive map", "mental representation of layout of " +
        "one's environment");
        yayQuestions.add(cogMap);
        Questions latentLearn = new Questions("latent learning", "learning that occurs but not "+
        "apparent until incentive to demonstrate it");
        yayQuestions.add(latentLearn);
        Questions overjustEffect = new Questions("overjustification effect", "effect of promising"+
        " reward for doing something someone was already going to do, person now sees rewards " +
        "rather than interest as motivation");
        yayQuestions.add(overjustEffect);
        Questions obserLearn = new Questions("observational learning", "learning by observing "+
        "others");
        yayQuestions.add(obserLearn);
        Questions modeling = new Questions("modeling", "process of observing and imitating "+
        "specific behaviour");
        yayQuestions.add(modeling);
        Questions prosocialBe = new Questions("prosocial behaviour", "positive/constructive/"+
        "heplful behaviour");
        yayQuestions.add(prosocialBe);
        Questions memory = new Questions("memory", "persistence of learning over time through " +
        "storage and retrieval of information");
        yayQuestions.add(memory);
        Questions flashMem = new Questions("flashbulb memory", "clear memory of emotionally " +
        "significant moment or event");
        yayQuestions.add(flashMem);
        Questions encoding = new Questions("encoding", "processing of information into memory "+
        "system, like by extracting meaning");
        yayQuestions.add(encoding);
        Questions storage = new Questions("storage", "retention of encoded info over time");
        yayQuestions.add(storage);
        Questions retrieval = new Questions("retrieval", "process of getting info out of " +
        "memory storage");
        yayQuestions.add(retrieval);
        Questions sensMem = new Questions("sensory memory", "immediate intial recording of " +
        "sensory info in the memory system");
        yayQuestions.add(sensMem);
        Questions stMem = new Questions("short term memory", "activated memory that holds a few "+
        "items briefly before the info is stored or forgotten");
        yayQuestions.add(stMem);
        Questions ltMem = new Questions("long term memory", "relatively permanent and limitless "+
        "storehouse of memory system");
        yayQuestions.add(ltMem);
        Questions workingMem = new Questions("working memory", "focuses on processing of briefly "+
        "stored info");
        yayQuestions.add(workingMem);
        Questions autoProcess = new Questions("automatic processing", "unconcious encoding of "+
        "incidental info");
        yayQuestions.add(autoProcess);
        Questions effProcess = new Questions("effortful processing", "encoding that requries "+
        "attention and concious effort");
        yayQuestions.add(effProcess);
        Questions rehearsal = new Questions("rehearsal", "concious repetition of info");
        yayQuestions.add(rehearsal);
        Questions spacingEff = new Questions("spacing effect", "tendency for distributed study to"+
        " yield better long-term retention than through massed study (cramming)");
        yayQuestions.add(spacingEff);
        Questions sePoEf = new Questions("serial position effect", "tendency to recall best the "+
        "first and last items in a list");
        yayQuestions.add(sePoEf);
        Questions semEncode = new Questions("semantic encoding", "encoding of meaning, including "+
        "meaning of words");
        yayQuestions.add(semEncode);
        Questions acouEncode = new Questions("acoustic encoding", "encoding of sound, especially "+
        "sound of words");
        yayQuestions.add(acouEncode);
        Questions visEncode = new Questions("visual encoding", "encoding of picture images");
        yayQuestions.add(visEncode);
        Questions imagery = new Questions("imagery", "mental pictures, powerful aid to effortful "+
        "processing, especially when combined with semantic encoding");
        yayQuestions.add(imagery);
        Questions chunking = new Questions("chunking", "organizing items into familiar, "+
        "manageable units, often occurs automatically");
        yayQuestions.add(chunking);
        Questions iconMem = new Questions("iconic memory", "momentary sensory memory of visual "+
        "stimuli, lasts no more than a few tenths of a second");
        yayQuestions.add(iconMem);
        Questions echoMem = new Questions("echoic memory", "momentary sensory memory of auditory "+
        "stimui, if attention is elsewhere, sounds/words can still be recalled within 3-4 "+
        "seconds");
        yayQuestions.add(echoMem);
        Questions lpt = new Questions("long term potentiation", "increase in a synapse's firing "+
        "potential after brief rapid stimulation, believed to be neural basis for learning" +
        " and memory");
        yayQuestions.add(lpt);
    }
    public void allTheBrainPartTerms() {
        Questions neuron = new Questions("neuron", 
        "building block of the nervous system");
        yayQuestions.add(neuron);
        Questions dendrite = new Questions("dendrite",
        "Branches designed to receive/send/and transport information");
        yayQuestions.add(dendrite);
        Questions axon = new Questions("axon",
        "transports messages to different muscles/glands in the body");
        yayQuestions.add(axon);
        Questions actPot = new Questions("action potential",
        "a neural impulse/brief electrical charge");
        yayQuestions.add(actPot);
        Questions myeShe = new Questions("myelin sheath",
        "layer of fatty tissue encasing fibers of neurons, enables greater " +
        "transmission speed");
        yayQuestions.add(myeShe);
        Questions threshold = new Questions("threshold",
        "the level of stimulation required to trigger a neural impulse");
        yayQuestions.add(threshold);
        Questions synapse = new Questions("synapse", "junction between axon tip of the sending" +
        " neuron and the dendrite/cell body of receiving neuron");
        yayQuestions.add(synapse);
        Questions neurotransmitters = new Questions("neurotransmitters", "chemical messengers" +
        " that traverse synaptic gaps between neurons, bind to recepter sites on the " +
        "receiving neuron");
        yayQuestions.add(neurotransmitters);
        Questions acetylcholine = new Questions("acetylcholine", 
        "neurotransmitter that triggers muscle contraction");
        yayQuestions.add(acetylcholine);
        Questions endorphins = new Questions("endorphins", "natural, opiatelike " +
        "neurotransmitters linked to pain control and pleasure");
        yayQuestions.add(endorphins);
        Questions nerSys = new Questions("nervous system", "electrochemical communication " + 
        "system, consists of all the nerve cells of peripheral and central nervous system");
        yayQuestions.add(nerSys);
        Questions cns = new Questions("central nervous system/CNS", "brain and spinal cord");
        yayQuestions.add(cns);
        Questions pns = new Questions("peripheral nervous system/PNS", "sensory and motor " + 
        "neurons that connect the central nervous system to rest of the body");
        yayQuestions.add(pns);
        Questions nerves = new Questions("nerves", "neural cables containing many axons");
        yayQuestions.add(nerves);
        Questions senNeu = new Questions("sensory neurons", "neurons that carry incoming " + 
        "information from sense receptors to central nervous system");
        yayQuestions.add(senNeu);
        Questions interneu = new Questions("interneurons", "central nervous system neurons" + 
        " that intervene between sensory inputs/motor outputs");
        yayQuestions.add(interneu);
        Questions motNeu = new Questions("motor neurons", "neurons that carry outgoing info " + 
        "from central nervous system to muscles and glands");
        yayQuestions.add(motNeu);
        Questions sns = new Questions("somatic nervous system/sns", "division of pns that " +
        "controls skeletal muscles");
        yayQuestions.add(sns);
        Questions ans = new Questions("autonomic nervous system/ans", "part of pns that controls"
        + "glands and muscles of internal organs");
        yayQuestions.add(ans);
        Questions symNS = new Questions("sympathetic nervous system", "division of ans that " + 
        "arouses the body, mobilize energy in stressful situations");
        yayQuestions.add(symNS);
        Questions parans = new Questions("parasympathetic nervous system", "division of ans that" +
        " calms body, conserves energy");
        yayQuestions.add(parans);
        Questions reflex = new Questions("reflex", "simple automatic response to sensory "+ 
        "stimulus, such as knee jerk response");
        yayQuestions.add(reflex);
        Questions neuNet = new Questions("neural networks", "interconnected neural cells, can " +
        "learn, feedback strengthens connections that produce certain results");
        yayQuestions.add(neuNet);
        Questions phrenology = new Questions("phrenology", "ill fated theory that bumps on the " +
        "skull could reveal mental abilities/character traits");
        yayQuestions.add(phrenology);
        Questions lesion = new Questions("lesion", "naturally or experimentally caused " +
        "desruction of brain tissue");
        yayQuestions.add(lesion);
        Questions eeg = new Questions("electroencephalogram/eeg", "amplified recording of waves" +
        " of electrical activity that sweeps across brain, measured by electrodes on scalp");
        yayQuestions.add(eeg);
        Questions ct = new Questions("computed tomography/ct", "series of xray photos taken " +
        "from different angles, combined by computer into composite representation of slice" +
        " through body");
        yayQuestions.add(ct);
        Questions pet = new Questions("positron emission tomography/pet", "visual display of" +
        " brain activity, uses radioactive form of glucose");
        yayQuestions.add(pet);
        Questions mri = new Questions("magnetic resonance imaging/mri", "technique using "+
        "magnetic fields/radio waves to produce images that allow us to see structures within" +
        " the brain");
        yayQuestions.add(mri);
        Questions brainstem = new Questions("brainstem", "oldest part/central core of brain, "+
        "responsible for automatic survival fuctions");
        yayQuestions.add(brainstem);
        Questions medulla = new Questions("medulla", "base of brainstem, controls heartbeat " +
        "and breathing");
        yayQuestions.add(medulla);
        Questions retFor = new Questions("reticular formation", "nerve network in brainstem that "+
        "plays important role in controlling arousal");
        yayQuestions.add(retFor);
        Questions thalamus = new Questions("thalamus", "brain's sensory switchboard, located " +
        "on top of brainstem, directs messages to sensory receiving areas in cortex, transmits" +
        " replies to cerebellum/medulla");
        yayQuestions.add(thalamus);
        Questions cerebellum = new Questions("cerebellum", "attached to rear of brainstem, "+
        "coordinates voluntary movement/balance");
        yayQuestions.add(cerebellum);
        Questions limSys = new Questions("limbic system", "doughnut shaped system of neural " +
        "structures, associated with emotions such as fear/aggression and drives such as " +
        "fear/sex, includes hippocampus, amygdala, hypothalamus");
        yayQuestions.add(limSys);
        Questions amygdala = new Questions("amygdala", "two almond shaped neural clusters, " +
        "components of limbic system, linked to emotion");
        yayQuestions.add(amygdala);
        Questions hypothalamus = new Questions("hypothalamus", "neural structure below thalamus,"+
        "directs maintenance activies like eating/drinking/bodytemp, governs endocrine system" +
        ", linked to emotion");
        yayQuestions.add(hypothalamus);
        Questions cerCor = new Questions("cereberal cortex", "interconnected neural cells, covers"+
        " cerebral hemispheres, ultimate control/info processing center");
        yayQuestions.add(cerCor);
        Questions glial = new Questions("glial cells", "cells in nervous system that support/" +
        "nourish protect neurons");
        yayQuestions.add(glial);
        Questions frontLo = new Questions("frontal lobe", "portion of cerebral cortex behind " +
        "forehead, involved in speaking/muscle movements/making plans/judgement");
        yayQuestions.add(frontLo);
        Questions pariLo = new Questions("parietal lobe", "portion of cerebral cortex at top/rear"+
        " of head, includes sensory cortex");
        yayQuestions.add(pariLo);
        Questions occLo = new Questions("occipital lobe", "portion of cerebral cortex at back of" +
        " head, includes visual areas that receive visual info from opposite visual field");
        yayQuestions.add(occLo);
        Questions tempLo = new Questions("temporal lobe", "portion of cerebral cortex above ears,"+
        " includes auditory areas, receives info primarily from opposite ear");
        yayQuestions.add(tempLo);
        Questions moCor = new Questions("motor cortex", "area at rear of frontal lobes that "+
        "controls voluntary movements");
        yayQuestions.add(moCor);
        Questions senCor = new Questions("sensory cortex", "area at rear of parietal lobes, " +
        "registers/processes body sensations");
        yayQuestions.add(senCor);
        Questions assoAreas = new Questions("association areas", "areas of cerebral cortex " +
        "not involved in primary motor or sensory functions, instead higher mental functions " +
        "(learning, remembering, thinking, speaking, etc");
        yayQuestions.add(assoAreas);
        Questions aphasia = new Questions("aphasia", "impairment of language, usually caused by "+
        "left hemisphere damage to Broca's Area or Wernicke's Area");
        yayQuestions.add(aphasia);
        Questions brocaArea = new Questions("Broca's Area", "area of the frontal lobe, usually "+
        "left hemisphere, directs muscle movements involved in speech");
        yayQuestions.add(brocaArea);
        Questions werArea = new Questions("Wernicke's Area", "usually in left temporal lobe, " +
        "involved in language comprehension and expression");
        yayQuestions.add(werArea);
        Questions plasticity = new Questions("plasticity", "brains capacity for modification, "+
        "evident in brain reorganization following damage/experiments on effects of experience "+
        "on brain development");
        yayQuestions.add(plasticity);
        Questions corCall = new Questions("corpus callosum", "large band of neural fibers "+
        "connecting two brain hemispheres, carries messages between them");
        yayQuestions.add(corCall);
        Questions splitBrain = new Questions("split brain", "condition in which two hemispheres "+
        "of brain are isolated by cutting connecting fibers between them");
        yayQuestions.add(splitBrain);
        Questions endSys = new Questions("endocrine system", "body's 'slow' chemical "+
        "communication system, set of glands that secretes hormones into bloodstream");
        yayQuestions.add(endSys);
        Questions hormones = new Questions("hormones", "chemical messengers, mostly made by "+
        "endocrine glands, produced in one tissue and affect another");
        yayQuestions.add(hormones);
        Questions adrGlands = new Questions("adrenal glands", "pair of endocrine glands above "+
        "kidney, secrete epinephrine and norepinephrine");
        yayQuestions.add(adrGlands);
        Questions pitGland = new Questions("pituitary gland", "regulates growth, controls other "+
        "endocrine glands");
        yayQuestions.add(pitGland);
    }
    
    public boolean checkContains(ArrayList<Questions> array, String checkingTerm) {
        //if questions contains the term you're trying to add
        boolean result = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getTerm().equals(checkingTerm)) {
                result = true;
            }
        }
        return result;
    }
}