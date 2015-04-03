

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.cishell.utilities.ArrayUtilities;
import org.cishell.utilities.FileUtilities;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public final class CSVFilePreprocessor {
	public static final int HEADER_INDEX = 0;
	public static final String DEFAULT_EMPTY_VALUE = "";
	public static final String DEFAULT_TITLE_PREFIX = "UNTITLED_";
	
	private CSVFilePreprocessor() {
	}
	
	/**
	 * Preprocess the CSV File, remove all the unmeaningful column
	 * and add titles to the untitled header's columns.
	 * @param fileName - The absolute CSV file path
	 * @return The processed temporary CSV file
	 * @throws IOException - Thrown while failed to access file 
	 * 
	 */
	public static void main(String args[]) throws IOException  {
		 execute(new File("/home/rohan/survey.csv"));
	}
	
	/**
	 * Preprocess the CSV File, remove all the unmeaningful column
	 * and add titles to the untitled header's columns.
	 * @param file - The original CSV file
	 * @return The processed temporary CSV file
	 * @throws IOException - Thrown while failed to access file 
	 */
	
	public static File execute(File file) throws IOException {
		
		if (file.exists()) {
			return preprocessCSVFile(file);
		}
		return file;
	}

	/**
	 * Preprocess the CSV File, remove all the unmeaningful column
	 * and add titles to the untitled header's columns.
	 * @param file - The original CSV file
	 * @return The processed temporary CSV file
	 * @throws IOException - Thrown while failed to access file 
	 */
	
	private static File preprocessCSVFile(File file) throws IOException {
		List<String[]> rows = readAllRowsFromCSVFile(file);
		/* Remove all the tailing empty string */
		
		cleanTailingEmptyStrings(rows);
		
		List<String[]> rowsResult = generateIntermediateCSV(rows);
		/* Get the highest column size */
		//int columnSize = validateColumnSize(rows);
		
		/* balance all the rows with the same column size */
		//balanceColumnsSizeOfTheRows(rows, columnSize);
		
		/* Add default title to the untitled header's columns */
		//titleHeader(rows);
		
		/* Create a temporary CSV file with the processed CSV contents */
	    System.out.println("fdfdfghj");
		
		File processedFile = createTempCSVFile(file);
		
		writeRowToCSVFile(processedFile, rowsResult, 0);
		
		
		return processedFile;
	}
	
	/*
	 * Return the highest column size in all the rows
	 */
	private static int validateColumnSize(List<String[]> rows) {
		int maxSize = 0;
		
		for (String[] row : rows) {
			if (maxSize < row.length) {
				maxSize = row.length;
			}
		}
		return maxSize;
	}
	
	/*
	 * fuzzy database
	 */
	private static List<String[]> generateIntermediateCSV(List<String[]> rows)
	{
		int i = 0;
		int noOfRow = 0 ;
		int j = 0;
		List<String[]> rowsResult = new ArrayList<String[]>();
		for(String[] row : rows)
		{
			System.out.println(noOfRow+"fghj");
			i=0;
			j=0;
			String[] rowResult = new String[365]; 
			
			if(noOfRow==0)
			{
				while(i<65)
				{
					if(i<3 && i>0)
					{
						rowResult[j++] = row[i]; 
					}
					else if( i>=3 && i<61 )
					{
						
						rowResult[j++] = "1" + row[i];
						rowResult[j++] = "2" + row[i];
						rowResult[j++] = "3" + row[i];
						rowResult[j++] = "4" + row[i];
						rowResult[j++] = "5" + row[i];
					}
					else if( i>=61 && i<65)
					{
						if(i<63)
						{
							rowResult[j++] = "1"+row[i];
							rowResult[j++] = "2"+row[i];
							rowResult[j++] = "3"+row[i];
							rowResult[j++] = "4"+row[i];
							rowResult[j++] = "5"+row[i];
							rowResult[j++] = "6"+row[i];
							rowResult[j++] = "7"+row[i];
						}
						else
						{
							rowResult[j++] = "1"+row[i];
							rowResult[j++] = "2"+row[i];
							rowResult[j++] = "3"+row[i];
							rowResult[j++] = "4"+row[i];
						}
						
						
					}
					i++;
				}
				
			}
			else
			{
			while(i<65)
			{
				if(i<3)
				{
					if(i==1)
					{
					if(row[i].contentEquals("Yes"))
					{
						rowResult[j++] = "0";
						
					}
					else
					{
						rowResult[j++] = "1";
					}
					}
					if(i==2)
					{
						if(Integer.parseInt(row[i])==1)
						rowResult[j++] = "Once";
						else rowResult[j++] = "Multiple";
					}
				}
				else if(i>=3 && i<61)
				{
					if(Integer.parseInt(row[i])==1)
					{
						//System.out.println("allla j");
						rowResult[j++] = "0.7";
						rowResult[j++] = "0.3";	
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.0";
					}
					else if(Integer.parseInt(row[i])==2)
					{
						rowResult[j++] = "0.2";
						rowResult[j++] = "0.6";	
						rowResult[j++] = "0.2";
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.0";
					}
					else if(Integer.parseInt(row[i])==3)
					{
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.2";	
						rowResult[j++] = "0.6";
						rowResult[j++] = "0.2";
						rowResult[j++] = "0.0";
					}
					else if(Integer.parseInt(row[i])==4)
					{
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.0";	
						rowResult[j++] = "0.2";
						rowResult[j++] = "0.6";
						rowResult[j++] = "0.2";
					}
					else if(Integer.parseInt(row[i])==5)
					{
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.0";		
						rowResult[j++] = "0.0";
						rowResult[j++] = "0.3";
						rowResult[j++] = "0.7";
					}
					
					
				}
				else if(i>=61 && i<65)
				{
					if(i<63)
					{
						if(Integer.parseInt(row[i])==1)
						 {
							rowResult[j++] = "0.8";
							rowResult[j++] = "0.1";	
							rowResult[j++] = "0.1";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							
						 }
						else if(Integer.parseInt(row[i])==2)
						{
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.6";		
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
						}
						else if(Integer.parseInt(row[i])==3)
						{
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.2";		
							rowResult[j++] = "0.6";
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
						}
						else if(Integer.parseInt(row[i])==4)
						{
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";		
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.6";
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
						}
						else if(Integer.parseInt(row[i])==5)
						{
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";		
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.6";
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.0";
						}
						else if(Integer.parseInt(row[i])==6)
						{
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";		
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.2";
							rowResult[j++] = "0.6";
							rowResult[j++] = "0.2";
						}
						else if(Integer.parseInt(row[i])==7)
						{
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";		
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.0";
							rowResult[j++] = "0.1";
							rowResult[j++] = "0.1";
							rowResult[j++] = "0.8";
						}
					
					
					
					}
					else if(i>=63)
					{
						if(row[i].contentEquals("Surely not"))
						{
							  rowResult[j++] = "0.8";
							  rowResult[j++] = "0.2";
							  rowResult[j++] = "0.0";
							  rowResult[j++] = "0.0";
						}
						if(row[i].contentEquals("Probably not"))
						{
						  rowResult[j++] = "0.4";
						  rowResult[j++] = "0.6";
						  rowResult[j++] = "0.0";
						  rowResult[j++] = "0.0";
						}
						if(row[i].contentEquals("Probably"))
						{
						  rowResult[j++] = "0.8";
						  rowResult[j++] = "0.2";
						  rowResult[j++] = "0.6";
						  rowResult[j++] = "0.2";
						}
						if(row[i].contentEquals("Surely"))
						{
						  rowResult[j++] = "0.8";
						  rowResult[j++] = "0.0";
						  rowResult[j++] = "0.2";
						  rowResult[j++] = "0.8";
						}
						
					}
					
				}
				 i++;
			 }
			}
			rowsResult.add(rowResult);
		  noOfRow++;
		}
		System.out.println("fdfdfghj");
		return rowsResult;
	}
	/*
	 * Add default title to the untitled columns
	 */
	private static void titleHeader(List<String[]> rows) {

		int numberOfUntitledColumn = 0;
		
		if (rows.size() > 0) {
			String[] header = rows.get(HEADER_INDEX);
			
			for (int i = 0; i < header.length; i++) {
				/* Add default title if the column is an empty string */
				if (header[i].equals(DEFAULT_EMPTY_VALUE)) {
					header[i] = DEFAULT_TITLE_PREFIX + String.valueOf(numberOfUntitledColumn++);
				}
			}
			rows.set(HEADER_INDEX, header);
		}
	}
	
	/*
	 * Loop through each row and remove the tailing emtpy strings if exist
	 */
	private static void cleanTailingEmptyStrings(List<String[]> rows) {
		for (int i = 0; i < rows.size(); i++) {
			rows.set(i, removeTailingEmptyStringsInRow(rows.get(i)));
		}
	}
	
	/*
	 * Remove the tailing empty strings from the given row. Example:
	 * Given {"abc", "", "text",,""}
	 * Result {"abc", "", "text"}
	 */
	private static String[] removeTailingEmptyStringsInRow(String[] row) {
		int size = row.length;
		int cuttedIndex = size;
		
		for (int i = cuttedIndex - 1; i >= 0; i--) {
			if (row[i].equals(DEFAULT_EMPTY_VALUE)) {
				cuttedIndex = i;
			} else {
				break;
			}
		}
		
		if (cuttedIndex != size) {
			return ArrayUtilities.copyOf(row, cuttedIndex);
		}
		return row;
	}
	
	/*
	 * Read all the rows from CSV file
	 */
	private static List<String[]> readAllRowsFromCSVFile(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		CSVReader csvReader = new CSVReader(fileReader);
		List<String[]> rows = csvReader.readAll();
		csvReader.close();
		
		return rows;
	}

	/*
	 * Create the temporary CSV file
	 */
	private static File createTempCSVFile(File file) throws IOException {
		String tempCSVFileName = "Preprocessed-" 
			+ FileUtilities.extractFileName(file.getName()) 
			+ "-";
		
		return new File("/home/rohan/"+tempCSVFileName+".csv");
	}
	
	/*
	 * Write the rows into the CSV file
	 */
	private static void writeRowToCSVFile(File file, List<String[]> rows, int columnSize) 
		throws IOException {
		CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
		csvWriter.writeAll(rows);
		csvWriter.close();
	}
	
	/*
	 * Expend the rows if needed 
	 */
	private static void balanceColumnsSizeOfTheRows(List<String[]> rows, int columnSize) {
		
		for (int i = 0; i < rows.size(); i++) {
			String[] row = rows.get(i);
			int remain = columnSize - row.length;
			
			/* Replace the expended row if needed */
			if (remain > 0) {
				rows.set(i, expendRowWithEmptyString(rows.get(i), remain));
			}
		}
	}
	
	/*
	 * Add columns with the given size into a row 
	 */
	private static String[] expendRowWithEmptyString(String[] row, int expendedSize) {
		if (expendedSize > 0) {
			int oldSize = row.length;
			
			/* Copy the array with additional size */
			row = ArrayUtilities.copyOf(row, oldSize + expendedSize);
			
			/* Fill null with DEFAULT_EMPTY_VALUE */
			for (int i = oldSize; i < row.length; i++) {
				row[i] = DEFAULT_EMPTY_VALUE;
			}
		}
		
		return row;
	}
}