/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.model;

/**
 *
 * @author Carlos Cortina
 */
public class ReportDiagnosticAuxiliar {
    
    private Cie10 diagnositc;
    private Long count;

    public ReportDiagnosticAuxiliar() {
    }

    public ReportDiagnosticAuxiliar(Cie10 diagnositc, Long count) {
        this.diagnositc = diagnositc;
        this.count = count;
    }

    public Cie10 getDiagnositc() {
        return diagnositc;
    }

    public void setDiagnositc(Cie10 diagnositc) {
        this.diagnositc = diagnositc;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
