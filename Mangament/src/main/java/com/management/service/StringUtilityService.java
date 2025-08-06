package com.management.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StringUtilityService {

    public Set<String> generatePermutations(String input) {
        Set<String> permutations = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return permutations;
        }

        generatePermutations("", input, permutations);
        return permutations;
    }

    private void generatePermutations(String prefix, String remaining, Set<String> permutations) {
        int n = remaining.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1, n),
                        permutations);
            }
        }
    }

}
