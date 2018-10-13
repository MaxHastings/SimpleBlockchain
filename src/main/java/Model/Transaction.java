package Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Transaction implements JSONNetworkObj,JSONSignedObj, JSONHashObj {

    private final static String JSON_INPUTS = "inputs";
    private final static String JSON_OUTPUTS = "outputs";

    private ArrayList<Input> inputs = new ArrayList<Input>();

    private ArrayList<Output> outputs = new ArrayList<Output>();

    public Input getInput(int index){
        return inputs.get(index);
    }

    public Output getOutput(int index){
        return outputs.get(index);
    }

    public void addInput(Input input){
        inputs.add(input);
    }

    public void addOutput(Output output){
        outputs.add(output);
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<Input> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<Output> outputs) {
        this.outputs = outputs;
    }

    public JsonObject toJson() {
        JsonObject txObj = new JsonObject();
        JsonArray inputArray = new JsonArray();
        JsonArray outputArray = new JsonArray();

        for(int i = 0; i < inputs.size(); i++) {
            Input input = inputs.get(i);
            inputArray.add(input.toJson());
        }

        for(int i = 0; i < outputs.size(); i++){
            Output output = outputs.get(i);
            outputArray.add(output.toJson());
        }

        txObj.add(JSON_INPUTS, inputArray);
        txObj.add(JSON_OUTPUTS, outputArray);

        return txObj;
    }


    public JsonObject toJSONForSigning() {
        JsonObject txObj = new JsonObject();
        JsonArray inputArray = new JsonArray();
        JsonArray outputArray = new JsonArray();

        for(int i = 0; i < inputs.size(); i++) {
            Input input = inputs.get(i);
            inputArray.add(input.toJSONForSigning());
        }

        for(int i = 0; i < outputs.size(); i++){
            Output output = outputs.get(i);
            outputArray.add(output.toJSONForSigning());
        }

        txObj.add(JSON_INPUTS, inputArray);
        txObj.add(JSON_OUTPUTS, outputArray);

        return txObj;
    }

    public JsonObject toJSONForHashing() {
        JsonObject txObj = new JsonObject();
        JsonArray inputArray = new JsonArray();
        JsonArray outputArray = new JsonArray();

        for(int i = 0; i < inputs.size(); i++) {
            Input input = inputs.get(i);
            inputArray.add(input.toJSONForHashing());
        }

        for(int i = 0; i < outputs.size(); i++){
            Output output = outputs.get(i);
            outputArray.add(output.toJSONForHashing());
        }

        txObj.add(JSON_INPUTS, inputArray);
        txObj.add(JSON_OUTPUTS, outputArray);

        return txObj;
    }
}
