package com.hiyoo.dao;
 /**
   * �洢���̲�������
   *
   */
  public class CallableParameter {
      /**
       * ��������
       */
     public String Name;
     /**
      * ����ֵ
      */
     public Object Value;
     /**
      * true��ʾ����Ϊ�������
      */
     public boolean OutPut;
     /**
      * ��������
      */
     public int Type;
     /**
      * �������Ͳ����Ĺ��캯��
      * @param name �洢���� �������� ��������
      * @param value �洢���� �������� ����ֵ
      */
     public CallableParameter(String name,Object value){
         this.Name = name;
         this.Value= value;
     }
     /**
      * ������Ͳ����Ĺ��캯��
      * @param type �洢���� ������� ��������
      * @param name �洢���� ������� ��������
      */
     public CallableParameter(int type,String name){
         this.Name = name;
         this.OutPut = true;
         this.Type = type;
     }
     /**
      * �������Ͳ����Ĺ��캯��
      * @param type �洢���� ��������
      */
     public CallableParameter(int type){
         this.Name = "";
         this.OutPut = true;
         this.Type = type;
     }
 }