package generateCodeTest;

import java.util.ArrayList;
import java.util.List;


public class GenerateCodeTest {
	
	public static List<String> getBoList(){
		List<String> boList = new ArrayList<String>();
		boList.add("DepositAccountCurrency");
		boList.add("DepositAccountCurrencyHistory");
		boList.add("DepositAccountCurrencyBalance");
		boList.add("DepositAccountCurrencyBalanceHistory");
		boList.add("DepositAccountCurrencyDailyBalance");
		
		boList.add("DepositAccountCurrencyDailyBalanceHistory");
		boList.add("DepositAccountTransaction");
		boList.add("DepositAccountTransactionHistory");
		boList.add("DepositAccountTax");
		boList.add("DepositAccountTaxHistory");
		
		boList.add("DepositAction");
		boList.add("DepositActionItem");
		boList.add("DepositActionType");
		boList.add("DepositCodeHead");
		boList.add("DepositCodeDetail");
		
		boList.add("DepositAccount");
		boList.add("DepositAccountHistory");
		boList.add("DepositPassbook");
		boList.add("DepositPassbookHistory");
		boList.add("DepositCertificater");
		boList.add("DepositCertificaterHistory");
		
		return boList;
	}
	
	public static String generateInterfaceCode(String obName){
		StringBuilder str = new StringBuilder();
		str.append("	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		str.append("	// " + obName + "\n");
		str.append("	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		str.append("	public " + obName + " get" + obName + "(Long id);\n");
		str.append("	// save\n");
		str.append("	public Long save" + obName + "(" + obName + " bo);\n");
		str.append("	public void update" + obName + "(" + obName + " bo);\n");
		str.append("	public void saveOrUpdate" + obName + "(" + obName + " bo);\n");
		str.append("	// batch save\n");
		str.append("	public void save" + obName + "(Collection<" + obName + "> bos);\n");
		str.append("	public void update" + obName + "(Collection<" + obName + "> bos);\n");
		str.append("	public void saveOrUpdate" + obName + "(Collection<" + obName + "> bos);\n");
		str.append("	// delete\n");
		str.append("	public void delete" + obName + "(Long id);\n");
		str.append("	public void delete" + obName + "(" + obName + " bo);\n");
		str.append("	public void delete" + obName + "s(Collection<" + obName + "> collection);\n");
		str.append("	public void delete" + obName + "s();\n");
		str.append("	// query\n");
		str.append("	public List<" + obName + "> get" + obName + "s();\n");
		str.append("	public List<" + obName + "> get" + obName + "s(Integer page, Integer pageSize, String orderBy, Boolean isAscending, Map<String, Object> params);\n");
		str.append("	public Integer get" + obName + "sCount(Map<String, Object> params);\n");
		return str.toString();
	}
	
	public static String generateImplementCode(String boName){
		StringBuilder str = new StringBuilder();
		str.append("	// ~~~~~~~~~~~~~~~~~~~~~~~~~ " + boName + " ~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		str.append("	public Long save" + boName + "(" + boName + " bo) {\n");
		str.append("		return " + trunFirstStrintToLower(boName) + "Dao.save(bo);");
		str.append("	}\n");
		str.append("	public void update" + boName + "(" + boName + " bo) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.update(bo);\n");
		str.append("	}\n");
		str.append("	public void saveOrUpdate" + boName + "(" + boName + " bo) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.saveOrUpdate(bo);\n");
		str.append("	}\n");
		str.append("	// batch save\n");
		str.append("	public void save" + boName + "(Collection<" + boName + "> bos) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.save(bos);\n");
		str.append("	}\n");
		str.append("	public void update" + boName + "(Collection<" + boName + "> bos) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.update(bos);\n");
		str.append("	}\n");
		str.append("	public void saveOrUpdate" + boName + "(Collection<" + boName + "> bos) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.saveOrUpdate(bos);\n");
		str.append("	}\n");
		str.append("	// delete\n");
		str.append("	public void delete" + boName + "(Long id) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.delete(id);\n");
		str.append("	}\n");
		str.append("	public void delete" + boName + "(" + boName + " bo) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.delete(bo);\n");
		str.append("	}\n");
		str.append("	public void delete" + boName + "s(Collection<" + boName + "> collection) {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.deleteAll(collection);\n");
		str.append("	}\n");
		str.append("	public void delete" + boName + "s() {\n");
		str.append("		" + trunFirstStrintToLower(boName) + "Dao.deleteAll();\n");
		str.append("	}\n");
		str.append("	public " + boName + " get" + boName + "(Long id) {\n");
		str.append("		return " + trunFirstStrintToLower(boName) + "Dao.get(id);\n");
		str.append("	}\n");
		str.append("	public List<" + boName + "> get" + boName + "s() {\n");
		str.append("		return " + trunFirstStrintToLower(boName) + "Dao.getAll();\n");
		str.append("	}\n");
		str.append("	public List<" + boName + "> get" + boName + "s(Integer page, Integer pageSize, String orderBy, Boolean isAscending, Map<String, Object> params) {\n");
		str.append("		return get(" + boName + ".class, page, pageSize, orderBy, isAscending, false, params);\n");
		str.append("	}\n");
		str.append("	public Integer get" + boName + "sCount(Map<String, Object> params) {\n");
		str.append("		List<Number> result = get(" + boName + ".class, 0, 1, null, true, true, params);\n");
		str.append("		if (CollectionUtils.isEmpty(result))\n");
		str.append("			return 0;\n");
		str.append("		else\n");
		str.append("			return result.get(0).intValue();\n");
		str.append("	}\n");
		return str.toString();
	}
	
	public static String generateImplementField(String boName){
		StringBuilder str = new StringBuilder();
//		str.append("	// ~~~~~~~~~~~~~~~~~~~~~~~~~ fields ~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		str.append("	protected " + boName + "Dao " + trunFirstStrintToLower(boName) + "Dao = null; \n");
		return str.toString();
	}
	
	public static String trunFirstStrintToLower(String str){
		String reStr = "";
		reStr = str.substring(0, 1).toLowerCase() + str.substring(1);
		return reStr;
	}
	
	public static String generateXmlBean(String boName){
		StringBuilder str = new StringBuilder();
		str.append("	<bean id=\"" + trunFirstStrintToLower(boName) + "Dao\" class=\"com.tsb.wbs.model.dep.dao.hibernate." + boName + "DaoImpl\" >\n");
		str.append("		<property name=\"hibernateTemplate\" ref=\"hibernateTemplate\" />\n");
		str.append("		<property name=\"batch_size\" value=\"${batch_size}\" />\n");
		str.append("	</bean>\n");
		return str.toString();
	}
	
	public static String generateXmlProperty(String boName){
		StringBuilder str = new StringBuilder();
		str.append("	<property name=\"" + trunFirstStrintToLower(boName) + "Dao\" ref=\"" + trunFirstStrintToLower(boName) + "Dao\" />\n");
		return str.toString();
	}
	
	public static void main(String[] args){
		List<String> list = getBoList();
		String outStr = new String();
		for(String str : list){
			outStr = generateXmlProperty(str);
			System.out.println(outStr);
		}
	}


}
