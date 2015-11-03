package fuzzylogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyLogic {

    public static void main(String[] args) throws Exception{
        // Load from 'FCL' file
        String fileName = "tipper.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
      
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        // Show 
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        functionBlock.setVariable("weight", 8);
        functionBlock.setVariable("dirty_level", 1);

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
}
