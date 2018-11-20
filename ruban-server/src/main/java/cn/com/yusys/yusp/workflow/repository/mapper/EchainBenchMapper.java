package cn.com.yusys.yusp.workflow.repository.mapper;

import java.util.List;

public interface EchainBenchMapper {

    List<String> selectInstanceId(String bizNo);

    int deleteWorkflowInfo(String instanceId);

}