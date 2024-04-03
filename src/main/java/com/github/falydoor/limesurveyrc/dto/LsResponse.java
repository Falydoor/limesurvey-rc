package com.github.falydoor.limesurveyrc.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

/**
 * Response DTO.
 */
public class LsResponse {
    
	@SerializedName("id")
    private int id;

    @SerializedName("startlanguage")
    private String language;

    private Integer lastpage;

    @SerializedName("submitdate")
    private LocalDate submitDate;

    private String seed;
    
    @SerializedName("startdate")
    private LocalDate startDate;
    
    private LocalDate datestamp;
    
    @SerializedName("NomeSolicitante")
    private String nomeSolicitante;
    
    @SerializedName("Representa")
    private String representa;
    
    @SerializedName("Email")
    private String email;
    
    @SerializedName("Telefone")
    private String telefone;
    
    @SerializedName("Processo1")
    private String processo1;
    
    @SerializedName("Processo2")
    private String processo2;
    
    @SerializedName("Processo3")
    private String processo3;
    
    @SerializedName("Processo4")
    private String processo4;
    
    @SerializedName("Processo5")
    private String processo5;
    
    @SerializedName("PoloAtivo")
    private String poloAtivo;
    
    @SerializedName("PoloPassivo")
    private String poloPassivo;
    
    @SerializedName("LocalizacaoAtual")
    private String localizacaoAtual;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getLastpage() {
		return lastpage;
	}

	public void setLastpage(Integer lastpage) {
		this.lastpage = lastpage;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(LocalDate datestamp) {
		this.datestamp = datestamp;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getRepresenta() {
		return representa;
	}

	public void setRepresenta(String representa) {
		this.representa = representa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProcesso1() {
		return processo1;
	}

	public void setProcesso1(String processo1) {
		this.processo1 = processo1;
	}

	public String getProcesso2() {
		return processo2;
	}

	public void setProcesso2(String processo2) {
		this.processo2 = processo2;
	}

	public String getProcesso3() {
		return processo3;
	}

	public void setProcesso3(String processo3) {
		this.processo3 = processo3;
	}

	public String getProcesso4() {
		return processo4;
	}

	public void setProcesso4(String processo4) {
		this.processo4 = processo4;
	}

	public String getProcesso5() {
		return processo5;
	}

	public void setProcesso5(String processo5) {
		this.processo5 = processo5;
	}

	public String getPoloAtivo() {
		return poloAtivo;
	}

	public void setPoloAtivo(String poloAtivo) {
		this.poloAtivo = poloAtivo;
	}

	public String getPoloPassivo() {
		return poloPassivo;
	}

	public void setPoloPassivo(String poloPassivo) {
		this.poloPassivo = poloPassivo;
	}

	public String getLocalizacaoAtual() {
		return localizacaoAtual;
	}

	public void setLocalizacaoAtual(String localizacaoAtual) {
		this.localizacaoAtual = localizacaoAtual;
	}

	@Override
	public String toString() {
		return "LsResponse [id=" + id + ", language=" + language + ", lastpage=" + lastpage + ", submitDate="
				+ submitDate + ", seed=" + seed + ", startDate=" + startDate + ", datestamp=" + datestamp
				+ ", nomeSolicitante=" + nomeSolicitante + ", representa=" + representa + ", email=" + email
				+ ", telefone=" + telefone + ", processo1=" + processo1 + ", processo2=" + processo2 + ", processo3="
				+ processo3 + ", processo4=" + processo4 + ", processo5=" + processo5 + ", poloAtivo=" + poloAtivo
				+ ", poloPassivo=" + poloPassivo + ", localizacaoAtual=" + localizacaoAtual + "]";
	}
    
    
    
    
}
