package com.crainyday.sport.excel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.crainyday.sport.mapper.GeneralMapper;
/**
 * 处理普通用户Excel信息的监听器
 * @author crainyday
 *
 */
public class ReadGeneralListener extends AnalysisEventListener<GeneralData>{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadGeneralListener.class);
    // 每隔50条存储数据库, 实际使用中可以3000条, 然后清理list, 方便内存回收
    private static final int BATCH_COUNT = 50;
    List<GeneralData> list = new ArrayList<GeneralData>();
	private GeneralMapper generalMapper;
	private Integer userId;
	private String prefix;
    public ReadGeneralListener(GeneralMapper generalMapper, Integer userId, String prefix) {
        this.generalMapper = generalMapper;
        this.userId = userId;
        this.prefix = prefix;
    }
    public void invoke(GeneralData data, AnalysisContext context) {
    	data.setIdentity(prefix+data.getIdentity());
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
	}
	public void doAfterAllAnalysed(AnalysisContext context) {
		// 这里也要保存数据，确保最后遗留的数据也存储到数据库
		if(list.size()>0) {
			saveData();
		}
        LOGGER.info("userId: "+userId + ". 所有数据解析完成！");
	}
    /**
     * 存储到数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        generalMapper.addGeneralByExcel(list, userId);
        LOGGER.info("存储数据库成功！");
    }
}
