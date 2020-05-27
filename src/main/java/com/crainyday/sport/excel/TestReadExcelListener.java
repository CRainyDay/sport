package com.crainyday.sport.excel;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * 测试处理Excel表格的处理器
 * @author crainyday
 *
 */
public class TestReadExcelListener extends AnalysisEventListener<GeneralData>{
//	private static final Logger LOGGER = LoggerFactory.getLogger(TestReadExcelListener.class);
    // 每隔5条存储数据库, 实际使用中可以3000条, 然后清理list, 方便内存回收
    private static final int BATCH_COUNT = 5;
    List<GeneralData> list = new ArrayList<GeneralData>();
    public TestReadExcelListener() {
    }
    public void invoke(GeneralData data, AnalysisContext context) {
//        LOGGER.info("解析到一条数据:{}", data.getIdentity());
        System.out.println("Id: "+data.getIdentity());
//        System.out.println("Class: "+data.getGeneralClass());
//        System.out.println("Name: "+data.getGeneralName());
    	list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
	}
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
//        LOGGER.info("所有数据解析完成！");
	}
    /**
     * 存储到数据库
     */
    private void saveData() {
//        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        LOGGER.info("存储数据库成功！");
    }
}
