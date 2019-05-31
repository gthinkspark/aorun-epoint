package com.aorun.epoint.service;

import com.aorun.epoint.model.WorkerMember;



/**
 * @author 作者 LG
 * @version 
 * @data 创建时间：2019年3月18日 下午7:39:28
 * 类说明	
 */
public interface WorkerMemberService {
	public WorkerMember findByWorkerId(Long workerId);

	public void updateWorkerMember(WorkerMember workerMember);

}
