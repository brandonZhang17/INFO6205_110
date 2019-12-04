package edu.neu.coe.info6205.life.base;

import java.util.Random;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.LineCrossover;
import io.jenetics.MeanAlterer;
import io.jenetics.Mutator;
import io.jenetics.Optimize;
import io.jenetics.PartialAlterer;
import io.jenetics.Phenotype;
import io.jenetics.RouletteWheelSelector;
import io.jenetics.SwapMutator;
import io.jenetics.TournamentSelector;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;
 
public class GAtwo {
    // 2.) Definition of the fitness function.
    private static int eval(Genotype<IntegerGene> gt) {
        return gt.getChromosome()
            .as(IntegerChromosome.class)
            .intValue();
    }
    
    public static String use(){
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
    	Random random = new Random();
    	int length = 2+random.nextInt(101);
        while (length%2 != 0) {
            length = 2+random.nextInt(101);
        }
        Factory<Genotype<IntegerGene>> gtf =
            Genotype.of(IntegerChromosome.of(-10,10,length));
        
        // 3.) Create the execution environment.
        Engine<IntegerGene, Integer> engine = Engine
            .builder(GAtwo::eval, gtf)
            .offspringFraction(0.7)
            .survivorsSelector(new RouletteWheelSelector<>())
            .offspringSelector(new TournamentSelector<>())
            .alterers(new SwapMutator<>())
            //.optimize(Optimize.MAXIMUM)
            .build();
        
        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<IntegerGene> result = engine.stream()
            .limit(100)
            .collect(EvolutionResult.toBestGenotype());
//      Phenotype<IntegerGene,Integer>best= engine.stream()
//		.limit(100)
//        .collect(EvolutionResult.toBestPhenotype());
        String string = "";
      for (int rank = 0; rank < length; rank++) {
          if (rank != 0 && rank % 2 == 0) {
        	  string = string+",";
          }
          string = string + result.getChromosome().getGene(rank).intValue();
          string = string + " ";
      }
        //System.out.println("Hello World:\n" + result);
        //System.out.println("Hello World:\n" + string);
        
        
//        Phenotype<IntegerGene,Integer>best= engine.stream()
//        		.limit(100)
//                .collect(EvolutionResult.toBestPhenotype());
//        String s1 = "";

        //System.out.println(best.toString());
        return string;
    }
}
