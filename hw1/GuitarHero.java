public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static double getFrequency(char c) {
        int index = keyboard.indexOf(c);
        if (index < 0) {
            throw new RuntimeException("The key is invalid!");
        } else {
            double initFreq = 440.0;
            double freq = initFreq * Math.pow(2, (index - 24) / 12);
            return freq;
        }
    }

    public static synthesizer.GuitarString[] getGuitarStringArray() {
        int keyNum = keyboard.length();
        synthesizer.GuitarString[] res = new synthesizer.GuitarString[keyNum];
        for (int i = 0; i < keyNum; i++) {
            char c = keyboard.charAt(i);
            double freq = getFrequency(c);
            res[i] = new synthesizer.GuitarString(freq);
        }
        return res;
    }

    public static double getSample(synthesizer.GuitarString[] synGArray) {
        double res = 0.0;
        for (int i = 0; i < synGArray.length; i++) {
            res += synGArray[i].sample();
        }
        return res;
    }

    public static void getTic(synthesizer.GuitarString[] synGArray) {
        for (int i = 0; i < synGArray.length; i++) {
            synGArray[i].tic();
        }
    }

    public static void main(String[] args) {
        synthesizer.GuitarString[] musicLab = getGuitarStringArray();

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    throw new RuntimeException("The key is invalid!");
                } else {
                    musicLab[index].pluck();
                }
            }

            double sample = getSample(musicLab);
            StdAudio.play(sample);
            getTic(musicLab);
        }

    }
}
