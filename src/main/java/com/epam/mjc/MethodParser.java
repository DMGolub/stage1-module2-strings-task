package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        int braceIndex = signatureString.indexOf('(');
        String beforeBrace = signatureString.substring(0, braceIndex);
        int splitLimit;
        if (beforeBrace.indexOf(' ') == beforeBrace.lastIndexOf(' ')) {
            splitLimit = 2;
        } else {
            splitLimit = 3;
        }
        String[] signatureParts = signatureString.split(" ", splitLimit);
        String returnType;
        String accessModifier = null;
        String nameAndArgs;
        if (signatureParts.length > 2) {
            accessModifier = signatureParts[0];
            returnType = signatureParts[1];
            nameAndArgs = signatureParts[2];
        } else {
            returnType = signatureParts[0];
            nameAndArgs = signatureParts[1];
        }
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        int openingBraceIndex = nameAndArgs.indexOf('(');
        String methodName = nameAndArgs.substring(0, openingBraceIndex);
        if (openingBraceIndex < nameAndArgs.length() - 2) {
            String[] argumentsArray = nameAndArgs
                    .substring(openingBraceIndex + 1, nameAndArgs.length() - 1)
                    .split(", ");

            for (int i = 0; i < argumentsArray.length; i++) {
                String[] typeAndName = argumentsArray[i].split(" ");
                String argType = typeAndName[0];
                String argName = typeAndName[1];
                MethodSignature.Argument argument = new MethodSignature.Argument(argType, argName);
                arguments.add(argument);
            }
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }
}