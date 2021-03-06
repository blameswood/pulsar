/**
 * Copyright 2016 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.pulsar.common.naming;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class NamedEntity {

    // allowed characters for property, namespace, cluster and destination names are
    // alphanumeric (a-zA-Z_0-9) and these special chars -=:.
    // % is allowed as part of valid URL encoding
    private static final Pattern NAMED_ENTITY_PATTERN = Pattern.compile("^[-=:.\\w]*$");

    public static void checkName(String name) throws IllegalArgumentException {
        Matcher m = NAMED_ENTITY_PATTERN.matcher(name);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid named entity: " + name);
        }
    }

    public static void checkURI(URI uri, String name) {
        if (!String.format("%s://%s%s", uri.getScheme(), uri.getHost(), uri.getPath()).equals(name)) {
            throw new IllegalArgumentException("Invalid trailing chars in named entity: " + name);
        }
    }

}