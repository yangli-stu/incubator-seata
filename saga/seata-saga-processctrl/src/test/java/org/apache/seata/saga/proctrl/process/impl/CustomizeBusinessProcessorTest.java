/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.seata.saga.proctrl.process.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.seata.common.exception.FrameworkException;
import org.apache.seata.saga.proctrl.ProcessContext;
import org.apache.seata.saga.proctrl.ProcessType;
import org.apache.seata.saga.proctrl.handler.ProcessHandler;
import org.apache.seata.saga.proctrl.handler.RouterHandler;
import org.apache.seata.saga.proctrl.impl.ProcessContextImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CustomizeBusinessProcessorTest
 */
public class CustomizeBusinessProcessorTest {

    @Test
    public void testProcessFail() {
        CustomizeBusinessProcessor customizeBusinessProcessor = new CustomizeBusinessProcessor();
        ProcessContext processContext = new ProcessContextImpl();
        processContext.setVariable(ProcessContext.VAR_NAME_PROCESS_TYPE, ProcessType.STATE_LANG);
        Map<String, ProcessHandler> processHandlerMap = new HashMap<>(1);
        processHandlerMap.put(ProcessType.STATE_LANG.getCode(), null);
        customizeBusinessProcessor.setProcessHandlers(processHandlerMap);
        Assertions.assertThrows(FrameworkException.class, () -> customizeBusinessProcessor.process(processContext));
    }

    @Test
    public void testRouteFail() {
        CustomizeBusinessProcessor customizeBusinessProcessor = new CustomizeBusinessProcessor();
        ProcessContext processContext = new ProcessContextImpl();
        Map<String, RouterHandler> routerHandlerMap = new HashMap<>(1);
        routerHandlerMap.put(ProcessType.STATE_LANG.getCode(), null);
        customizeBusinessProcessor.setRouterHandlers(routerHandlerMap);
        Assertions.assertDoesNotThrow(() -> customizeBusinessProcessor.route(processContext));
    }
}