package com.juqitech.service.utils.query.result;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserPropertyFilter implements ResultProcessor {

	private static final List<String> USER_PROPERTY_NAMES = new ArrayList<String>();

	{
		USER_PROPERTY_NAMES.add("userOID");
		USER_PROPERTY_NAMES.add("cellPhone");
		USER_PROPERTY_NAMES.add("userName");
		USER_PROPERTY_NAMES.add("createTime");
		USER_PROPERTY_NAMES.add("userSource");
		USER_PROPERTY_NAMES.add("total");
		USER_PROPERTY_NAMES.add("couponCount");
		USER_PROPERTY_NAMES.add("orderCount");
	}

	@Override
	public void process(List<Map<String, Object>> result) {
		for (Map<String, Object> map : result) {
			Timestamp createTime = (Timestamp) map.get("createTime");
			if (createTime != null) {
				map.put("couponCreateTime", createTime);
			}
			String cellphone = (String) map.get("cellphone");
			if (cellphone == null) {
				map.put("cellphone", "");
			}
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (!USER_PROPERTY_NAMES.contains(key)) {
					it.remove();
				}
			}
		}
	}
}
