package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerMemberMapper;
import com.aorun.epoint.model.WorkerMember;
import com.aorun.epoint.service.WorkerMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author 作者 LG
 * @version 
 * @data 创建时间：2019年3月18日 下午7:43:40
 * 类说明	
 */
@Service
public class WorkerMemberServiceImpl  implements WorkerMemberService{


	private static final Logger LOGGER = LoggerFactory.getLogger(WorkerMemberServiceImpl.class);

	@Autowired
	private WorkerMemberMapper workerMemberMapper;


	@Override
	public WorkerMember findByWorkerId(Long workerId) {
		return workerMemberMapper.selectByPrimaryKey(workerId);
	}

	@Override
	public void updateWorkerMember(WorkerMember workerMember) {
		workerMemberMapper.updateByPrimaryKeySelective(workerMember);
	}


}