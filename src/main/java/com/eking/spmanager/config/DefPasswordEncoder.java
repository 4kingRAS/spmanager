package com.eking.spmanager.config;

/**
 * @Author Yulin.Wang
 * @Date 2019-01-24
 * @Description
 **/

import org.springframework.security.crypto.password.PasswordEncoder;

public class DefPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
