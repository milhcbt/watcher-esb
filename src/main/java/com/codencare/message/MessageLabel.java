/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.message;

/**
 *
 * @author abah
 */
public interface MessageLabel {

    /**
     * must added
     */
    String RAW_MESSAGE = "msg";///must
    String MESSAGE_SOURCE = "msg-source";//must
    String MESSAGE_VALUE = "msg-value";//must
    String DIGITAL1 = "digit1";
    String DIGITAL2 = "digit2";
    String DIGITAL3 = "digit3";
    String DIGITAL4 = "digit4";
    String ANALOG1 = "analog1";
    String ANALOG2 = "analog2";
    String ANALOG3 = "analog3";
    String ANALOG4 = "analog4";
    String URGENT = "urgent";//must
    int VALUE_LOW = -1;
    int VALUE_HIGH = 1;
    String ID = "id";
    int MESSAGE_IN = 1;
    int MESSAGE_OUT = 2;
}
