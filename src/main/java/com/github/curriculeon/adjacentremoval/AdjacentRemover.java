package com.github.curriculeon.adjacentremoval;

public class AdjacentRemover {
    private String input;
    private boolean isWendysTurns;

    public AdjacentRemover(String input) {
        this.input = input;
        this.isWendysTurns = true;
    }

    public boolean canMoveEither() {
        return canMoveBob() || canMoveWendy();
    }

    public boolean canMoveBob() {
        return input.contains("bbb");
    }

    public boolean canMoveWendy() {
        return input.contains("www");
    }

    public void play() {
        final StringBuilder sb = new StringBuilder(this.input);
        while (canMoveEither()) {
            String characterToEvaluate = "www";
            if(!canMoveWendy()) {
                break;
            }
            if (!isWendysTurns) {
                characterToEvaluate = "bbb";
                if(!canMoveBob()) {
                    break;
                }
            }
            final int indexOfSubstring = sb.indexOf(characterToEvaluate);
            final int indexOfMiddleCharacter = indexOfSubstring + 1;
            sb.deleteCharAt(indexOfMiddleCharacter);
            this.input = sb.toString();
            isWendysTurns = !isWendysTurns;
        }
    }

    public String solve() {
        if (canMoveWendy() && !canMoveBob()) {
            return "wendy";
        }
        if (!canMoveWendy() && canMoveBob()) {
            return "bob";
        }


        if (canMoveEither()) {
                play();
                if(isWendysTurns) {
                    return "bob";
                } else {
                    return "wendy";
                }
        } else {
            return "bob";
        }
    }
}
