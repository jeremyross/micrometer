/*
 * Copyright 2017 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.atlas;

import io.micrometer.core.instrument.AbstractMeter;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.util.MeterEquivalence;

public class SpectatorCounter extends AbstractMeter implements Counter {
    private com.netflix.spectator.api.Counter counter;

    SpectatorCounter(Meter.Id id, com.netflix.spectator.api.Counter counter) {
        super(id);
        this.counter = counter;
    }

    @Override
    public void increment(double amount) {
        counter.increment((long) amount);
    }

    @Override
    public double count() {
        return counter.count();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return MeterEquivalence.equals(this, o);
    }

    @Override
    public int hashCode() {
        return MeterEquivalence.hashCode(this);
    }
}
