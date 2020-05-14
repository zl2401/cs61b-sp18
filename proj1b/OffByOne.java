public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char first, char second) {
        int diff = first - second;
        int gapNum = 1;
        if ((diff == gapNum) || (diff == -gapNum)) {
            return true;
        }
        else {
            return false;
        }
    }
}
