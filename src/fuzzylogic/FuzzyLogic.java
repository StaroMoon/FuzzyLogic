package fuzzylogic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyLogic {

    static JTextField t1;
    static JTextField t2;
    static JFrame frame;
    public static void main(String[] args) throws Exception{
        // Load from 'FCL' file
        String fileName = "tipper.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        
        /*t1 = new JTextField();
        t2 = new JTextField();
        setValue(t1,"weight");
        setValue(t2,"dirty level");*/
        
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        // Show 
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        functionBlock.setVariable("weight", 8);
        functionBlock.setVariable("dirty_level", 10);
        //functionBlock.setVariable("weight", Double.parseDouble(t1.getText()));
        //functionBlock.setVariable("dirty_level", Double.parseDouble(t2.getText()));

        // Evaluate
        functionBlock.evaluate();

        // Show output variable's chart
        Variable water_level = functionBlock.getVariable("water_level");
        Variable time = functionBlock.getVariable("time");
        
        JFuzzyChart.get().chart(water_level, water_level.getDefuzzifier(), true);
        JFuzzyChart.get().chart(time, time.getDefuzzifier(), true);

        // Print ruleSet
        System.out.println(functionBlock);
    }
    
    public static void setValue(JTextField tf,String type){
        frame = new JFrame();
        double x = 0;
        String str = "";
        while(str.equals("")){
            str = JOptionPane.showInputDialog("Insert your " + type);
            try{
                x = Double.parseDouble(str);
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(frame,"Invalid Input type " + e,"Invalid type", JOptionPane.ERROR_MESSAGE);
                str = "";
            }
            if(x > 10 || x < 0){
                JOptionPane.showMessageDialog(frame, "Input out of range only 0-10 are allowed","Out of range",JOptionPane.ERROR_MESSAGE);
                str = "";
            }else{}
        }
        System.out.println(x);
        tf.setText(x + "");
    }
    
}
