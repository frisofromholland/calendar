package org.kulk.web.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * User: frisokulk
 * Date: 6/14/16
 * Time: 10:03 PM
 */
@RequiredArgsConstructor
public class PageParameterKeyValuePair {

    @Getter
    PageParameterKey key;

    @Getter
    String value;
}
