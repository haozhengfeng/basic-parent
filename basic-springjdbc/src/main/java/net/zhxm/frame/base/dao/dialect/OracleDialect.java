package net.zhxm.frame.base.dao.dialect;

import net.zhxm.frame.base.dao.Page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("oracleDialect")
public class OracleDialect implements IDialect {

	@Override
	public String getPageSql(String sql, String orderby, Page page) {
		// 设置分页参数
		int satrt = (page.getPageIndex() - 1) * page.getPageSize() + 1;
		int end = page.getPageIndex() * page.getPageSize();

		StringBuilder sb = new StringBuilder();
		sb.append("select * from ( select rownum frame_page_sql_row_number");
		sb.append(",frame_sql_temp_table1.* from (");
		sb.append(sql);
		if (StringUtils.isNotBlank(orderby)) {
			sb.append(" ").append(orderby);
		}
		sb.append(") frame_sql_temp_table1 where rownum <= ");
		sb.append(end);
		sb.append(") frame_sql_temp_table2");
		sb.append("");
		sb.append(" where frame_sql_temp_table2.frame_page_sql_row_number >= ");
		sb.append(satrt);

		return sb.toString();
	}

	@Override
	public String getDataDaseType() {
		return "oracle";
	}

	@Override
	public boolean isRowNumber() {
		return true;
	}

}
