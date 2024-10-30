package vttp.batch5.sdf.task01;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	private static String holidayString(boolean holiday){
		if (holiday == true){
			return "a holiday";
		}
		else return "not a holiday";
	}

	public static void main(String[] args) throws IOException {

		//System.out.printf("hello, world\n");
		String csvPath = "task01/day.csv";
		try{
			csvPath = args[0];
		}catch(ArrayIndexOutOfBoundsException e ){
			System.err.println("Please add csvfile path as an arguement. In this case it is task01/day.csv");
			throw e;
		}

		List<BikeEntry> bikeList = new ArrayList<>();
		FileReader fr = new FileReader(csvPath);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		line = br.readLine();
		while ((line = br.readLine()) != null){
			String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //split with commas (CSV)
			BikeEntry bike = BikeEntry.toBikeEntry(lineArray);
			bikeList.add(bike);
		}

		List<BikeEntry> topBikes = new ArrayList<>();
		Comparator<BikeEntry> comparator = Comparator.comparingInt(p -> p.getCasual()+p.getRegistered());
		BikeEntry firstBike = bikeList.stream().max(comparator).get();
		topBikes.add(firstBike);
		bikeList.remove(firstBike);
		//System.out.println(firstBike.getCasual() + firstBike.getRegistered()); //print
		BikeEntry secondBike = bikeList.stream().max(comparator).get();
		topBikes.add(secondBike);
		bikeList.remove(secondBike);
		//System.out.println(firstBike.getCasual() + firstBike.getRegistered());//print
		BikeEntry thirdBike = bikeList.stream().max(comparator).get();
		topBikes.add(thirdBike);
		bikeList.remove(thirdBike);
		BikeEntry fourthBike = bikeList.stream().max(comparator).get();
		topBikes.add(fourthBike);
		bikeList.remove(fourthBike);
		BikeEntry fifthBike = bikeList.stream().max(comparator).get();
		topBikes.add(fifthBike);

		List<String> orderList = new ArrayList<>();
		orderList.add("highest");
		orderList.add("second highest");
		orderList.add("third highest");
		orderList.add("fourth highest");
		orderList.add("fifth highest");


		Map<Integer,String> weatherMap = new HashMap<>();
		weatherMap.put(1, "Clear, Few clouds, Partly cloudy, Partly cloudy");
		weatherMap.put(2, "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist");
		weatherMap.put(3, "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds");
		weatherMap.put(4, "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog");

		for (int i = 0; i < topBikes.size(); i ++){
			String paragraph = "The %s (position) recorded number of cyclists was in %s (season), on a %s (day) in the month of %s (month).\nThere were a total of %d (total) cyclists. The weather was %s (weather).\n%s (day) was %s \n\n"
				.formatted(orderList.get(i),
				Utilities.toSeason(topBikes.get(i).getSeason()),
				Utilities.toWeekday(topBikes.get(i).getWeekday()),
				Utilities.toMonth(topBikes.get(i).getMonth()),
				topBikes.get(i).getCasual()+topBikes.get(i).getRegistered(),
				weatherMap.get(topBikes.get(i).getWeather()),
				Utilities.toWeekday(topBikes.get(i).getWeekday()),
				Main.holidayString(topBikes.get(i).isHoliday())
				);
				System.out.println(paragraph);
		}
		

	}
}
