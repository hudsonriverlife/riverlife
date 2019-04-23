package edu.columbia.riverLife.presentation.pf;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named(value="mainBean")
@Scope("session")
public class FishBean implements Serializable {

}
