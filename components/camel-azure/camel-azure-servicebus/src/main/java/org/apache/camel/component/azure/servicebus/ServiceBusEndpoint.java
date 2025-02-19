/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.azure.servicebus;

import org.apache.camel.Category;
import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.support.DefaultEndpoint;

/**
 * Send and receive messages to/from Azure Event Bus.
 */
@UriEndpoint(firstVersion = "3.12.0", scheme = "azure-servicebus", title = "Azure ServiceBus",
             syntax = "azure-servicebus:queueNameOrTopicName", category = {
                     Category.CLOUD, Category.MESSAGING })
public class ServiceBusEndpoint extends DefaultEndpoint {

    @UriParam
    private ServiceBusConfiguration configuration;

    public ServiceBusEndpoint(final String uri, final Component component, final ServiceBusConfiguration configuration) {
        super(uri, component);
        this.configuration = configuration;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new ServiceBusProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        final Consumer eventHubConsumer = new ServiceBusConsumer(this, processor);
        configureConsumer(eventHubConsumer);

        return eventHubConsumer;
    }

    /**
     * The component configurations
     */
    public ServiceBusConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ServiceBusConfiguration configuration) {
        this.configuration = configuration;
    }
}
