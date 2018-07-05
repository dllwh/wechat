package com.cdeledu.util.application.email;

import java.util.List;
import java.util.Map;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @Description: 获取邮件夹列表
 * @author: 独泪了无痕
 * @date: 2015年9月15日 上午10:28:32
 * @version: V1.0
 * @history:
 */
final class GetMailFoldersHelper {

	public static List<Map<String, Object>> GetMailFolders(Store store) throws MessagingException {
		List<Map<String, Object>> resultList = Lists.newArrayList();
		Folder rootFolder = store.getDefaultFolder();// 默认父目录
		Folder[] folders = rootFolder.list();// 默认目录列表
		Map<String, Object> resultMap = null;
		for (Folder folder : folders) {
			try {
				resultMap = Maps.newHashMap();
				resultMap.put("folderName", folder.getName());
				resultMap.put("messageCount", store.getFolder(folder.getName()).getMessageCount());
				resultList.add(resultMap);
			} catch (Exception e) {

			}
		}
		return resultList;
	}
}
