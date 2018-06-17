package com.cdeledu.service.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cdeledu.dao.BaseDaoSupport;
import com.cdeledu.model.cms.resources.Attachment;
import com.cdeledu.service.cms.AttachmentService;

@Service("attachmentService")
@SuppressWarnings("unchecked")
public class AttachmentServiceImpl implements AttachmentService {
	/** ----------------------------------------------------- Fields start */
	@Resource
	private BaseDaoSupport<?> baseDao;
	private static final String PREFIX = "com.cdeledu.dao.impl.cms.AttachmentDaoImpl.";

	/** ----------------------------------------------------- Fields end */
	@Override
	public List<Attachment> getAttachmentList(Attachment attachment) throws Exception {
		return (List<Attachment>) baseDao.findListForJdbcParam(PREFIX + "getAttachmentList",
				attachment);
	}

	@Override
	public Integer getAttachmentCount(Attachment attachment) {
		try {
			return baseDao.getCountForJdbcParam(PREFIX + "getAttachmentCount", attachment);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
