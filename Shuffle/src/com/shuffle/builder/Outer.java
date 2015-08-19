package com.shuffle.builder;

public class Outer {

	private String name;
	
	private int age;
	
	public static class Builder {
		private String name;
		
		private int age;
		
		public Builder withAge(int age) {
			this.age = age;
			
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			
			return this;
		}
		public Outer build() {
			 return new Outer(this);
		}
	}
	
	private Outer(Builder builder) {
		this.age = builder.age;
		this.name = builder.name;
	}
	
	
	@Override
	public String toString() {
		return "Outer [name=" + name + ", age=" + age + "]";
	}



	public static void main(String[] args) {
		Outer outer = new Outer.Builder().withAge(2).build();
		
		System.out.println(outer);
	}
}
