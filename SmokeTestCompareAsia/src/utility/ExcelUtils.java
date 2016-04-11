package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String[][] arrayExcelData = null;

	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method
	public static XSSFSheet setExcelFile(String sPath, String sSheetName) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(sPath);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sSheetName);
			// Log.info("Class ExcelUtil | Method setExcelFile | Sheet Opens");
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method setExcelFile | Exception desc : " + e.getMessage());
			throw (e);
		}
		return ExcelWSheet;
	}

	public static void setExcelSheet(XSSFWorkbook xWBook, String sSheetName) throws Exception {
		try {
			// Open the Excel file
			// sSheetName = ExcelUtils.getCellData(iTestCaseRow,
			// Constant.Col_TestCaseName);
			// FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWSheet = ExcelWBook.getSheet(sSheetName);
			// Log.info("Class ExcelUtil | Method setExcelSheet |" +
			// sSheetName);
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method setExcelSheet | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(XSSFSheet ExcelWSheet, int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			// Log.info("Class ExcelUtil | Method getCellData |" + CellData);
			return CellData;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getCellData | Exception desc : " + e.getMessage());
			return "";
		}
	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters
	@SuppressWarnings("static-access")
	public static void setCellData(String sResult, int RowNum, int ColNum) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(sResult);
			} else {
				Cell.setCellValue(sResult);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.UsrDir+Constant.Path_TestData+Constant.File_TestData);
			ExcelWBook.write(fileOut);
			// Log.info("Class ExcelUtil | Method setCellData |" + Cell);
			fileOut.flush();
			fileOut.close();
			// ExcelUtils.closeExcels(Constant.Path_TestData +
			// Constant.File_ObjectsSheet);
			// ExcelUtils.closeExcels(Constant.Path_TestData +
			// Constant.File_DataSheet);
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method setCellData | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static int getRowContains(XSSFSheet ExcelWSheet, String sTestCaseName, int colNum) throws Exception {
		int i;
		try {
			int rowCount = ExcelUtils.getRowUsed(ExcelWSheet);
			for (i = 0; i < rowCount; i++) {
				if (ExcelUtils.getCellData(ExcelWSheet, i, colNum).equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
			// Log.info("Class ExcelUtil | Method getRowContains |" + rowCount +
			// "i" + i + "sTestCaseName" + sTestCaseName);
			return i;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static int getUniqueRow(XSSFSheet ExcelWSheet, String sStepNo, String sStepName) throws Exception {
		int i;
		String sStep;
		String sStepN;
		int var;
		try {
			int rowCount = ExcelUtils.getRowUsed(ExcelWSheet);
			for (i = 1; i < rowCount; i++) {
				// Cell.setCellType(Cell.CELL_TYPE_STRING);
				Cell = ExcelWSheet.getRow(i).getCell(Constant.Col_StepNo);
				if (Cell.getCellType() == 0) {
					var = (int) Cell.getNumericCellValue();
					sStep = String.valueOf(var);
				} else {
					sStep = Cell.toString();
				}
				
				Cell = ExcelWSheet.getRow(i).getCell(Constant.Col_StepName);
				if (Cell.getCellType() == 0) {
					var = (int) Cell.getNumericCellValue();
					sStepN = String.valueOf(var);
				} else {
					sStepN = Cell.toString();
				}
				
				if (sStep.equals(sStepNo.replace(".0", "")) && sStepN.equalsIgnoreCase(sStepName)) {
					break;
				}
			}
			// Log.info("Class ExcelUtil | Method getRowContains |" + rowCount +
			// "i" + i + "sTestCaseName" + sTestCaseName);
			return i;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static int getColumnContains(XSSFSheet ExcelWSheet, String strcolVal, int rowNum) throws Exception {
		int i;
		try {
			int colCount = ExcelUtils.getcolumnCount(ExcelWSheet);
			for (i = 0; i < colCount; i++) {
				if (ExcelUtils.getCellData(ExcelWSheet, 0, i).equalsIgnoreCase(strcolVal)) {
					break;
				}
			}
			// Log.info("Class ExcelUtil | Method getRowContains |" + colCount +
			// "i" + i + "strcolVal" + strcolVal);
			return i;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static int getRowUsed(XSSFSheet ExcelWSheet) throws Exception {
		try {
			int RowCount = ExcelWSheet.getLastRowNum();
			// Log.info("Class ExcelUtil | Method getRowUsed |RowCount < " +
			// RowCount + " >.");
			return RowCount;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRowUsed | Exception desc : " + e.getMessage());
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static int getcolumnCount(XSSFSheet ExcelWSheet) throws Exception {
		try {
			Row = ExcelWSheet.getRow(0);
			int columnCount = Row.getLastCellNum();
			// Log.info("Class ExcelUtil | Method getcolumnCount |columnCount <
			// " + columnCount + " >.");
			return columnCount;
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getcolumnCount | Exception desc : " + e.getMessage());
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static String[][] getRows(XSSFSheet ExcelWSheet, String sTestCaseName) throws Exception {
		try {
			System.out.println("ExcelUtils|| getRows");
			int totalNoOfRows = ExcelWSheet.getLastRowNum();
			Row = ExcelWSheet.getRow(0);
			int totalNoOfCols = Row.getLastCellNum();
			arrayExcelData = new String[totalNoOfRows][totalNoOfCols + 2];
			ArrayList<String> sRowActionData;
			System.out.println("ROWS/COLUMNS" + totalNoOfRows + " " + totalNoOfCols);
			for (int i = 1; i <= totalNoOfRows; i++) {
				try {
					File file = new File(Constant.UsrDir+Constant.Path_TestData+ Constant.File_TestData);
					// Open WorkBook Test Data
					XSSFWorkbook ExcelObjectSheet = new XSSFWorkbook(file);
					// Open Sheet By Test Case Name in Test Data
					XSSFSheet ExcelOSheet = ExcelObjectSheet.getSheet(sTestCaseName);
					// Get First Row in Action Sheet
					Row = ExcelWSheet.getRow(i);
					sRowActionData = ExcelUtils.refineData(sTestCaseName);
					if (sRowActionData.size() != 0) {
						System.out.println("sRowActionData.size" + sRowActionData.size());
						for (int k = 0; k < sRowActionData.size(); k++) {
							arrayExcelData[i - 1][k] = sRowActionData.get(k);
							System.out.println("arrayExcelData   " + arrayExcelData[i - 1][k]);
						}
					}
					// System.out.println("sRowActionData" +sRowActionData);
				} catch (Exception e) {
					Log.error("Class ExcelUtil | Method getRows |For Loop i");
					System.out.println(e.getMessage());
					throw (e);
				}
			}
			// Log.info("Class ExcelUtil | Method getRows |Total number of Rows
			// < " + totalNoOfRows + " >.");
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRows | Exception desc : " + e.getMessage());
			e.printStackTrace();
			throw (e);
		}
		return arrayExcelData;
	}

	// Open Object sheet and find Object Row
	public static ArrayList<String> locateRowObject(String sTestCaseName, String sObjectId) throws Exception {
		System.out.println("ExcelUtils|| locateRowObject");
		ArrayList<String> srowData = new ArrayList<String>();
		File file = new File(Constant.UsrDir+Constant.Path_TestData + Constant.File_ObjectsSheet);
		XSSFWorkbook ExcelObjectSheet = new XSSFWorkbook(file);
		XSSFSheet ExcelOSheet = ExcelObjectSheet.getSheet(sTestCaseName);
		try {
			// System.out.println("ExcelUtils|| LocateRow");
			int iRow = ExcelUtils.getRowContains(ExcelOSheet, sObjectId, 0);
			Row = ExcelOSheet.getRow(0);
			int iCols = Row.getLastCellNum();
			Row = ExcelOSheet.getRow(iRow);
			for (int i = 1; i < iCols; i++) {
				if (Row.getCell(i) == null)
					srowData.add("");
				else
					srowData.add(Row.getCell(i).toString());
			}
			System.out.println("srowData" + srowData);
			// Log.info("Class ExcelUtil | Method getRow");
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRows");
			System.out.println(e.getMessage());
			throw (e);
		}
		return srowData;
	}

	// Open Data sheet and find Column Data
	public static ArrayList<String> locateColumnData(String sTestCaseName, String sObjectId, String sDataId,
			String sArgs) throws Exception {
		System.out.println("ExcelUtils|| locateColumnData");
		ArrayList<String> scolumnData = new ArrayList<String>();
		File file = new File(Constant.UsrDir+Constant.Path_TestData+ Constant.File_DataSheet);
		// OPCPackage opcPackage = OPCPackage.open(file.getAbsolutePath());
		XSSFWorkbook ExcelDataSheet = new XSSFWorkbook(file);
		XSSFSheet ExcelDSheet = ExcelDataSheet.getSheet(sTestCaseName);
		// Cell.setCellType(Cell.CELL_TYPE_STRING);
		try {
			// System.out.println("ExcelUtils|| locateColumnData");
			int iCol;
			int iRow = ExcelUtils.getRowContains(ExcelDSheet, sDataId, 0);
			if (sArgs == "") {
				iCol = ExcelUtils.getColumnContains(ExcelDSheet, sObjectId, iRow);
			} else {
				iCol = ExcelUtils.getColumnContains(ExcelDSheet, sArgs, iRow);
			}
			Row = ExcelDSheet.getRow(iRow);
			if (Row.getCell(iCol) != null) {
				if (Row.getCell(iCol).getCellType() == 0) {
					int i = (int) Row.getCell(iCol).getNumericCellValue();
					// strCellValue = String.valueOf(i);
					scolumnData.add(String.valueOf(i));
				} else {
					scolumnData.add(Row.getCell(iCol).toString());
				}
			}
			System.out.println("scolumnData" + scolumnData);
			// Log.info("Class ExcelUtil | Method getRows |Total number of Rows
			// < ");
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method locateColumnData ");
			System.out.println(e.getMessage());
			throw (e);
		}
		return scolumnData;
	}

	public static ArrayList<String> refineData(String sTestCaseName) throws Exception {
		String sStepNo = null;
		String sStepName = null;
		String sExecuted = null;
		String sScreenPointer = null;
		String sObjectId = null;
		String sDataId = null;
		String sFunction = null;
		String sArgs = null;
		String sResult = null;
		ArrayList<String> sRowActionData = null;
		ArrayList<String> srowObjectData = null;
		ArrayList<String> scolData = null;
		try {
			// srowObjectData = new ArrayList<String>();
			sRowActionData = new ArrayList<String>();
			// Assigning column Values to Constants
			if (Row.getCell(Constant.Col_StepNo) == null)
				sStepNo = "";
			else
				sStepNo = Row.getCell(Constant.Col_StepNo).toString();
			if (Row.getCell(Constant.Col_StepName) == null)
				sStepName = "";
			else
				sStepName = Row.getCell(Constant.Col_StepName).toString();
			if (Row.getCell(Constant.Col_ExecFlag) == null)
				sExecuted = "";
			else
				sExecuted = Row.getCell(Constant.Col_ExecFlag).toString();
			if (Row.getCell(Constant.Col_ScreenPointer) == null)
				sScreenPointer = "";
			else
				sScreenPointer = Row.getCell(Constant.Col_ScreenPointer).toString();
			if (Row.getCell(Constant.Col_ObjectIdentifier) == null)
				sObjectId = "";
			else
				sObjectId = Row.getCell(Constant.Col_ObjectIdentifier).toString();
			if (Row.getCell(Constant.Col_DataIdentifier) == null)
				sDataId = "";
			else
				sDataId = Row.getCell(Constant.Col_DataIdentifier).toString();
			if (Row.getCell(Constant.Col_Action) == null)
				sFunction = "";
			else
				sFunction = Row.getCell(Constant.Col_Action).toString();
			if (Row.getCell(Constant.Col_Args) == null)
				sArgs = "";
			else
				sArgs = Row.getCell(Constant.Col_Args).toString();
			if (Row.getCell(Constant.Col_Result) == null)
				sResult = "";
			else
				sResult = Row.getCell(Constant.Col_Result).toString();
			// Check Execution Flag
			if (!sExecuted.equals(null)) {
				// Adding Object Sheet Values to List when Data is empty
				if (sDataId.equals("") && !sObjectId.equals("")) {
					srowObjectData = ExcelUtils.locateRowObject(sScreenPointer, sObjectId);
				}
				// Adding Sheet Values to List when Object is empty
				else if (!sDataId.equals("") && sObjectId.equals("")) {
					scolData = ExcelUtils.locateColumnData(sScreenPointer, sObjectId, sDataId, sArgs);
				}
				// Adding Object/Data Sheet Values to List when Data is not
				// empty
				else if (!sDataId.equals("") && !sObjectId.equals("")) {
					srowObjectData = ExcelUtils.locateRowObject(sScreenPointer, sObjectId);
					scolData = ExcelUtils.locateColumnData(sScreenPointer, sObjectId, sDataId, sArgs);
				}
				// Adding Action Sheet Values to List as Is
				sRowActionData.add(sStepNo);
				sRowActionData.add(sStepName);
				sRowActionData.add(sExecuted);
				if (srowObjectData != null) {
					sRowActionData.addAll(srowObjectData);
				} else {
					for (int i = 0; i < 4; i++)
						sRowActionData.add("");
				}
				if (scolData != null)
					sRowActionData.add(scolData.get(0));
				else
					sRowActionData.add("");
				sRowActionData.add(sFunction);
				sRowActionData.add(sArgs);
				System.out.println("sRowActionData" + sRowActionData);
			}
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method getRows |For Loop i");
			System.out.println(e.getMessage());
			throw (e);
		}
		return sRowActionData;
	}

	public static void closeExcels(String spath) throws Exception {
		try {
			FileOutputStream fileOut = new FileOutputStream(spath);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			Log.error("Class ExcelUtil | Method closeExcels | Exception desc : " + e.getMessage());
			throw (e);
		}
	}
}