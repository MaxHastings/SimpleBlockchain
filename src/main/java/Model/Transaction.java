package Model;

import java.util.ArrayList;

public class Transaction implements DataObject {

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

    public String getObjData() {
        String data = "";
        for(int i = 0; i < inputs.size(); i++){
            Input input = inputs.get(i);
            data += input.getObjData();
        }
        for(int i = 0; i < outputs.size(); i++){
            Output output = outputs.get(i);
            data += output.getObjData();
        }
        return data;
    }
}
