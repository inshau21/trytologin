package mygroup.myproject;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
	private final ResourceLoader rscLoader;
	
	public ReportService(ResourceLoader rscLoader) {
		this.rscLoader = rscLoader;
	}
	
    public void generateReport(String templateName, Map<String, Object> parameters, List<UserModel_afterJoin> dataSource, OutputStream outputStream) throws JRException {
        // 讀取.jrxml或.jasper文件
        // "classpath:"等於"/myproject/src/main/resources/"demo_Blank_A4.jrxml
    	String templatePath = "classpath:" + templateName + ".jrxml";
        System.out.println("template(.jrxml)Path= " + templatePath);
    	// 假設直接載入.jasper檔,請使用本行: 
    	//String templatePath = "classpath:" + templateName + ".jasper";
        //System.out.println("template(.jasper)Path= " + templatePath);
        Resource rsc = rscLoader.getResource(templatePath);

        // 確保模板文件存在
        try (InputStream inputStream = rsc.getInputStream()) {
        	// 使用JasperCompileManager將jrxml模板編譯成jasper文件
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        	// 假設直接載入.jasper檔,請使用本行: 
            //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            
            // 把數據來源和Jasper報表進行綁定
            JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);

            // 資料被填入報表之內
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

            // 匯出為PDF
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception e) {
        	//e.printStackTrace();
        	throw new RuntimeException("報表生成失敗: " + e.getMessage(), e);
        }
    }
}