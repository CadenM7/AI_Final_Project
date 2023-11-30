package Blackjack.Game;

public class BlackjackAI {

    private double[][] q;
    private int[][] visits;
    private int targetVisits;
    private double discount, rateConstant;
    private int lastState, lastAction;


    public double getLearningRate(int state, int action) {
        double v = visits[state][action]/rateConstant;
        double pre = 1 + v;
        return (double) 1/pre;
    }


    public int getBestAction(int state) {
        int best = 0;
        double ql = q[state][0];
        for(int i = 0; i < q[state].length; i++){
            if(ql < q[state][i]) {
                best = i;
                ql = q[state][i];
            }
        }
        return best;
    }

    public boolean isExploring(int state) {
        for(int i = 0; i < visits[state].length; i++){
            if(visits[state][i] < targetVisits){
                return true;
            }
        }
        return false;
    }

    public int leastVisitedAction(int state) {
        int least = 0;
        int v = visits[state][0];
        for(int i = 0; i < visits[state].length; i++){
            if(visits[state][i] < v) {
                least = i;
                v = visits[state][i];

            }
        }
        return least;
    }

    public int senseActLearn(int newState, double reward) {
        int bestActionIndex = getBestAction(newState);
        double temp = (1- getLearningRate(lastState, lastAction)) * q[lastState][lastAction] +
                getLearningRate(lastState, lastAction) * (discount * q[newState][bestActionIndex] + reward);
        q[lastState][lastAction] = temp;
        visits[lastState][lastAction] += 1;
        if(isExploring(newState)){
            bestActionIndex = leastVisitedAction(newState);
        }
        lastAction = bestActionIndex;
        lastState = newState;

        return bestActionIndex;
    }

    public BlackjackAI(int states, int actions, int startState, int targetVisits, int rateConstant, double discount) {
        this.targetVisits = targetVisits;
        this.rateConstant = rateConstant;
        this.discount = discount;
        q = new double[states][actions];
        visits = new int[states][actions];
        lastState = startState;
        lastAction = 0;
    }

    private BlackjackAI() {}

    static BlackjackAI from(String s) {
        BlackjackAI result = new BlackjackAI();
        String[] lines = s.split("\n");
        String[] values1 = lines[0].split(";");
        result.targetVisits = Integer.parseInt(values1[0].split(":")[1]);
        result.discount = Double.parseDouble(values1[1].split(":")[1]);
        result.rateConstant = Double.parseDouble(values1[2].split(":")[1]);
        result.lastState = Integer.parseInt(values1[3].split(":")[1]);
        result.lastAction = Integer.parseInt(values1[4].split(":")[1]);

        boolean createdArrays = false;
        for (int i = 1; i < lines.length; i++) {
            String[] topSplit = lines[i].split(":");
            int state = Integer.parseInt(topSplit[0]);
            assert state == i - 1;
            String[] nextSplit = topSplit[1].split(",");
            for (int action = 0; action < nextSplit.length; action++) {
                String[] innerSplit = nextSplit[action].split("\\(");
                if (!createdArrays) {
                    int numStates = lines.length - 1;
                    int numActions = innerSplit.length;
                    result.q = new double[numStates][numActions];
                    result.visits = new int[numStates][numActions];
                    createdArrays = true;
                }
                result.q[state][action] = Double.parseDouble(innerSplit[0]);
                result.visits[state][action] = Integer.parseInt(innerSplit[1].substring(0, innerSplit[1].length() - 1));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("targetVisits:");
        result.append(targetVisits);
        result.append(";discount:");
        result.append(discount);
        result.append(";rateConstant:");
        result.append(rateConstant);
        result.append(";lastState:");
        result.append(lastState);
        result.append(";lastAction:");
        result.append(lastAction);
        result.append('\n');
        for (int state = 0; state < q.length; ++state) {
            result.append(state);
            result.append(':');
            for (int action = 0; action < q[state].length; action++) {
                result.append(q[state][action]);
                result.append('(');
                result.append(visits[state][action]);
                result.append(')');
                result.append(',');
            }
            result.deleteCharAt(result.length() - 1);
            result.append('\n');
        }
        return result.toString();
    }

    public double getQ(int state, int action) {
        return q[state][action];
    }

    public int getLastState() {
        return lastState;
    }

    public int getLastAction() {
        return lastAction;
    }
}
