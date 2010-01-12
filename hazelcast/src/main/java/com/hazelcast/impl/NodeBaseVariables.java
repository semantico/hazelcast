/* 
 * Copyright (c) 2008-2010, Hazel Ltd. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hazelcast.impl;

import com.hazelcast.impl.base.Call;
import com.hazelcast.impl.base.EventQueue;
import com.hazelcast.nio.Address;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class NodeBaseVariables {
    final LinkedList<MemberImpl> lsMembers = new LinkedList<MemberImpl>();

    final Map<Address, MemberImpl> mapMembers = new HashMap<Address, MemberImpl>(100);

    final Map<Long, Call> mapCalls = new ConcurrentHashMap<Long, Call>();

    final EventQueue[] eventQueues = new EventQueue[BaseManager.EVENT_QUEUE_COUNT];

    final Map<Long, StreamResponseHandler> mapStreams = new ConcurrentHashMap<Long, StreamResponseHandler>();

    final AtomicLong localIdGen = new AtomicLong(0);

    final Address thisAddress;

    final MemberImpl thisMember;

    NodeBaseVariables(Address thisAddress, MemberImpl thisMember) {
        this.thisAddress = thisAddress;
        this.thisMember = thisMember;
        for (int i = 0; i < BaseManager.EVENT_QUEUE_COUNT; i++) {
            eventQueues[i] = new EventQueue();
        }
    }
}