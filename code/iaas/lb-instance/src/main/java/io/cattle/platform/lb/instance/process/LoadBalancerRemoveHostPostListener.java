package io.cattle.platform.lb.instance.process;

import io.cattle.platform.core.constants.AgentConstants;
import io.cattle.platform.core.constants.CommonStatesConstants;
import io.cattle.platform.core.constants.InstanceConstants;
import io.cattle.platform.core.constants.LoadBalancerConstants;
import io.cattle.platform.core.model.Agent;
import io.cattle.platform.core.model.Host;
import io.cattle.platform.core.model.Instance;
import io.cattle.platform.core.model.LoadBalancer;
import io.cattle.platform.core.model.LoadBalancerHostMap;
import io.cattle.platform.engine.handler.HandlerResult;
import io.cattle.platform.engine.handler.ProcessPostListener;
import io.cattle.platform.engine.process.ProcessInstance;
import io.cattle.platform.engine.process.ProcessState;
import io.cattle.platform.engine.process.impl.ProcessCancelException;
import io.cattle.platform.lb.instance.service.LoadBalancerInstanceManager;
import io.cattle.platform.object.process.StandardProcess;
import io.cattle.platform.process.common.handler.AbstractObjectProcessLogic;
import io.cattle.platform.process.common.util.ProcessUtils;
import io.cattle.platform.util.type.CollectionUtils;
import io.cattle.platform.util.type.Priority;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoadBalancerRemoveHostPostListener extends AbstractObjectProcessLogic implements ProcessPostListener, Priority {

    @Inject
    LoadBalancerInstanceManager lbInstanceManager;

    @Override
    public String[] getProcessNames() {
        return new String[] { LoadBalancerConstants.PROCESS_LB_HOST_MAP_REMOVE };
    }

    @Override
    public HandlerResult handle(ProcessState state, ProcessInstance process) {
        LoadBalancerHostMap map = (LoadBalancerHostMap) state.getResource();
        LoadBalancer lb = loadResource(LoadBalancer.class, map.getLoadBalancerId());
        Long hostId = map.getHostId();
        boolean validHostState = false;
        if (hostId == null) {
            validHostState = true;
        } else {
            Host host = objectManager.loadResource(Host.class, hostId);
            if (host.getRemoved() == null
                    && !(host.getState().equalsIgnoreCase(CommonStatesConstants.REMOVED) || host.getState()
                            .equalsIgnoreCase(CommonStatesConstants.REMOVING))) {
                validHostState = true;
            }
        }

        if (validHostState) {
            removeLoadBalancerInstance(lb, map);
            removeLoadBalancerAgent(lb, map);
        }
        return null;
    }

    @Override
    public int getPriority() {
        return Priority.DEFAULT;
    }

    protected void removeLoadBalancerInstance(LoadBalancer loadBalancer, LoadBalancerHostMap hostMap) {
        Instance lbInstance = lbInstanceManager.getLoadBalancerInstance(hostMap);
        if (lbInstance != null
                && !(lbInstance.getState().equalsIgnoreCase(CommonStatesConstants.REMOVED) || lbInstance.getState()
                        .equals(
                        CommonStatesConstants.REMOVING))) {
            // try to remove first
            try {
                objectProcessManager.scheduleStandardProcess(StandardProcess.REMOVE, lbInstance, null);
            } catch (ProcessCancelException e) {
                objectProcessManager.scheduleProcessInstance(InstanceConstants.PROCESS_STOP, lbInstance, CollectionUtils.asMap(InstanceConstants.REMOVE_OPTION,
                        true));
            }
        }
    }

    protected void removeLoadBalancerAgent(LoadBalancer loadBalancer, LoadBalancerHostMap hostMap) {
        Agent lbAgent = lbInstanceManager.getLoadBalancerAgent(loadBalancer, hostMap);
        if (lbAgent == null) {
            return;
        }

        if (lbAgent.getRemoved() == null
                && !(lbAgent.getState().equalsIgnoreCase(CommonStatesConstants.REMOVED) || lbAgent.getState().equals(
                        CommonStatesConstants.REMOVING))) {
            // try to remove first
            try {
                objectProcessManager.scheduleStandardProcess(StandardProcess.REMOVE, lbAgent, null);
            } catch (ProcessCancelException e) {
                objectProcessManager.scheduleStandardProcess(StandardProcess.DEACTIVATE, lbAgent,
                        ProcessUtils.chainInData(new HashMap<String, Object>(),
                                AgentConstants.PROCESS_DEACTIVATE, AgentConstants.PROCESS_REMOVE));
            }
        }
    }
}
