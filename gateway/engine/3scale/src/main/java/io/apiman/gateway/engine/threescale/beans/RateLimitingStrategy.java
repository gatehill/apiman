/*
 * Copyright 2017 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apiman.gateway.engine.threescale.beans;

public enum RateLimitingStrategy {
    STANDARD, // Standard naive RL scheme. First call is sync and creates cache entry; subsequent requests are async until invalidated.
    BATCHED_HYBRID, // First call is sync and creates cache entry; subsequent requests are batched until TTL or size limit met. Batch is flushed and async authrep done.
    BATCHED_NO_3SCALE_RATE_LIMITING, // Just do batching, no 3scale RL (caveat emptor!)
    XC_EMULATION; // Emulated XC

    public static RateLimitingStrategy valueOfOrDefault(String name, RateLimitingStrategy defaultValue) {
        if (name == null) {
            return defaultValue;
        }
        return RateLimitingStrategy.valueOf(name.trim().toUpperCase());
    }
}
