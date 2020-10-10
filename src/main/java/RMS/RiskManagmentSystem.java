package RMS;

import java.util.HashMap;

public class RiskManagmentSystem {
    private static HashMap<String, float[]> riskDB = new HashMap<String, float[]>();

    public static void main(String[] args) {
        // "INN",{riskLevel,intervalValue}
        riskDB.put("105356343345", new float[] {0.2f, 400.0f});
        riskDB.put("77543549323", new float[] {0.8f, 700.0f});
        riskDB.put("32394534577", new float[] {0.8f, 700.0f});
        riskDB.put("7743787817", new float[] {0.8f, 700.0f});
    }

    public float getRisks(String inn) {
        if(riskDB.containsKey(inn)) {
            return riskDB.get(inn)[0];
        }
        return 0.7f;
    }
    public float getInterval(String inn) {
        if(riskDB.containsKey(inn)) {
            return riskDB.get(inn)[1];
        }
        return 0.7f;
    }

}