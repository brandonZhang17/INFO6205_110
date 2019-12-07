package edu.neu.coe.info6205.life.base;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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
import io.jenetics.engine.EvolutionStream;
import io.jenetics.engine.Limits;
import io.jenetics.util.Factory;

import static io.jenetics.engine.Limits.bySteadyFitness;

public class GAtwo {
    // 2.) Definition of the fitness function.
    private static int eval(Genotype<IntegerGene> gt) {
        return gt.getChromosome()
            .as(IntegerChromosome.class)
            .intValue();
    }
    
    public static Phenotype<IntegerGene, Integer> use(){
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
    	Random random = new Random();
    	int length = 4+random.nextInt(31);
        while (length%2 != 0) {
            length = 4+random.nextInt(31);
        }
        Factory<Genotype<IntegerGene>> gtf =
            Genotype.of(IntegerChromosome.of(-10,10,length));
        
        // 3.) Create the execution environment.
        Engine<IntegerGene, Integer> engine = Engine
            .builder(GAtwo::eval, gtf)
            .optimize(Optimize.MAXIMUM)
            .offspringFraction(0.7)
            .survivorsSelector(new RouletteWheelSelector<>())
            .offspringSelector(new TournamentSelector<>())
            .alterers(new SwapMutator<>())
            .build();
        
        // 4.) Start the execution (evolution) and collect the result.
//        Genotype<IntegerGene> result = engine.stream()
//            .limit(100)
//            .collect(EvolutionResult.toBestGenotype());
//        
//        int count = 0;
//        while(count<100) {
//        	Engine<IntegerGene, Integer> engine1 = Engine
//                    .builder(GAtwo::eval, result)
//                    .offspringFraction(0.7)
//                    .survivorsSelector(new RouletteWheelSelector<>())
//                    .offspringSelector(new TournamentSelector<>())
//                    .alterers(new SwapMutator<>())
//                    .build();
//        	result = engine1.stream()
//                    .limit(100)
//                    .collect(EvolutionResult.toBestGenotype());
//        	count++;
//        	//System.out.println("................");
//        }
//        Engine<IntegerGene, Integer> engine2 = Engine
//                .builder(GAtwo::eval, result)
//                .offspringFraction(0.7)
//                .survivorsSelector(new RouletteWheelSelector<>())
//                .offspringSelector(new TournamentSelector<>())
//                .alterers(new SwapMutator<>())
//                .build();
      Phenotype<IntegerGene, Integer> best = engine.stream()
    		  .limit(bySteadyFitness(20))
    		  .limit(100)
    		  .collect(EvolutionResult.toBestPhenotype());
//       System.out.println(best.toString());
        return best;
    }
    public static ArrayList<String> ev(String string){
    	ArrayList<String> result = new ArrayList<>();
    	ArrayList<Point> list = new ArrayList<>();
		for (String w : string.split(", *")) {
			String[] ws = w.split(" ");
			int x = Integer.parseInt(ws[0]);
			int y = Integer.parseInt(ws[1]);
			Point point = new Point(x, y);
			list.add(point);
		}
		int num = 0;
		while(num<10) {
			ArrayList<Point> start = list;
			Random random = new Random();
			int number = random.nextInt(start.size());
			Point add1 = new Point(start.get(number).getX()+1, start.get(number).getY()+1);
			Point add2 = new Point(start.get(number).getX()-1, start.get(number).getY()-1);
//			Point add3 = new Point(start.get(number).getX()+1, start.get(number).getY()-1);
//			Point add4 = new Point(start.get(number).getX()-1, start.get(number).getY()+1);
			while(avoidsame(add1, start)==false&&avoidsame(add2, start)==false) {
				number = random.nextInt(start.size());
				add1 = new Point(start.get(number).getX()+1, start.get(number).getY()+1);
				add2 = new Point(start.get(number).getX()-1, start.get(number).getY()-1);
//				add3 = new Point(start.get(number).getX()+1, start.get(number).getY()-1);
//				add4 = new Point(start.get(number).getX()-1, start.get(number).getY()+1);
			}
			if(avoidsame(add1, start)==true) {
				start.add(add1);
			}
			if(avoidsame(add2, start)==true) {
				start.add(add2);
			}
//			if(avoidsame(add3, start)==true) {
//				start.add(add1);
//			}
//			if(avoidsame(add4, start)==true) {
//				start.add(add2);
//			}
			String target = "";
			for(Point p: start) {
				if(target=="") {
	        		target = target + p.getX() +" "+ p.getY();
	        	}else {
	        		target = target + "," +" "+p.getX()+" "+p.getY();
	        	}
			}
			result.add(target);
			num++;
		}
		return result;
		
    }
    public static ArrayList<String> mu(String string) {
    	ArrayList<String> result = new ArrayList<>();
		ArrayList<Point> save = new ArrayList<>(); 
    	ArrayList<Point> list = new ArrayList<>();
		for (String w : string.split(", *")) {
			String[] ws = w.split(" ");
			int x = Integer.parseInt(ws[0]);
			int y = Integer.parseInt(ws[1]);
			Point point = new Point(x, y);
			list.add(point);
		}
		int num = 0;
		while(num<10) {
			ArrayList<Point> start = list;
			ArrayList<Point> orignal = save;
			Random random = new Random();
			int number = random.nextInt(start.size());
			for(int i = 0; i< number;i++) {
				int number1 = random.nextInt(start.size());
				Point point1 = start.get(number1);
				Point point1new = new Point(point1.getY(), point1.getX());
				start.remove(number1);
				if(avoidsame(point1new, save)==true) {
					orignal.add(point1new);
				}
			}
			for(Point p: start) {
				if(avoidsame(p, save)==true) {
					orignal.add(p);
				}
			}
			String target = "";
			for(Point p: save) {
				if(target=="") {
	        		target = target + p.getX() +" "+ p.getY();
	        	}else {
	        		target = target + "," +" "+p.getX()+" "+p.getY();
	        	}
			}
			result.add(target);
			num++;
		}
		
		

		return result;
	}
	
	private static boolean avoidsame(Point point1new, ArrayList<Point> result) {
		// TODO Auto-generated method stub
		for(Point p: result) {
			if(point1new.getX()==p.getX()&&point1new.getY()==p.getY()) {
				return false;
			}
		}
		return true;
	}
		
}
