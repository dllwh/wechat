package com.cdeledu.service.cms;

import java.util.List;

import com.cdeledu.model.cms.resources.Attachment;

public interface AttachmentService {
	List<Attachment> getAttachmentList(Attachment attachment) throws Exception;

	Integer getAttachmentCount(Attachment attachment);
}
