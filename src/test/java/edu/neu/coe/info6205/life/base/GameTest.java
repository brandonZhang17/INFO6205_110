package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.life.library.Library;
import io.jenetics.IntegerGene;
import io.jenetics.Phenotype;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

public class GameTest {

		@Test
		public void testRunBlip() {
				String patternName = "Blip";
				System.out.println("Game of Life with starting pattern: " + patternName);
				final String pattern = Library.get(patternName);
				final Game.Behavior generations = Game.run(0L, pattern);
				assertEquals(0, generations.generation);
		}

		@Test
		public void testRunBlinker() {
				String patternName = "Blinker";
				System.out.println("Game of Life with starting pattern: " + patternName);
				final String pattern = Library.get(patternName);
				final Game.Behavior generations = Game.run(0L, pattern);
				assertEquals(new Game.Behavior(2, 0, 1), generations);
		}
		@Test
		public void testfitness() {
			String p =  "Glider3" ;
			final String pString = Library.get(p);
			final long fitness = Game.fitnessCal(pString);
			assertEquals(9L, fitness);
			
		}
		@Test
		public void testselect() {
			String result = "";
			ArrayList<String> list = new ArrayList<>();
			String patternName1 = "Glider3";
			String patternName2 = "Blinker";
			final String pattern1 = Library.get(patternName1);
			final String pattern2 = Library.get(patternName2);
			list.add(pattern1);
			list.add(pattern2);
			HashMap<Long, String> map = Game.select(list);
			for(Long key: map.keySet()) {
				result = map.get(key);
			}
			assertEquals("0 0, 1 0, 2 0, 2 1, 1 2", result);
		}
		@Test
		public void testExpression() {
			Phenotype<IntegerGene, Integer> phenotype = GAtwo.use();
			String String1 = phenotype.toString();
			String String2 = phenotype.getGenotype().toString();
			assertTrue(String1!=String2);
		}
		@Test
		public void testmutation() {
			String p =  "Glider3" ;
			final String pString = Library.get(p);
			ArrayList<String>coun = GAtwo.mu(pString);
			assertTrue(coun.size()!=0);
		}
		@Test
		public void testevolution() {
			String p =  "Glider3" ;
			final String pString = Library.get(p);
			ArrayList<String>coun = GAtwo.ev(pString);
			assertTrue(coun.size()!=0);
		}
		@Test
		public void generation() {
				// TODO implement test
		}
}