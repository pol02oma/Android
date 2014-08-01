package org.apache.james.mime4j.stream;

public enum EntityState
{
  static
  {
    T_END_MESSAGE = new EntityState("T_END_MESSAGE", 1);
    T_RAW_ENTITY = new EntityState("T_RAW_ENTITY", 2);
    T_START_HEADER = new EntityState("T_START_HEADER", 3);
    T_FIELD = new EntityState("T_FIELD", 4);
    T_END_HEADER = new EntityState("T_END_HEADER", 5);
    T_START_MULTIPART = new EntityState("T_START_MULTIPART", 6);
    T_END_MULTIPART = new EntityState("T_END_MULTIPART", 7);
    T_PREAMBLE = new EntityState("T_PREAMBLE", 8);
    T_EPILOGUE = new EntityState("T_EPILOGUE", 9);
    T_START_BODYPART = new EntityState("T_START_BODYPART", 10);
    T_END_BODYPART = new EntityState("T_END_BODYPART", 11);
    T_BODY = new EntityState("T_BODY", 12);
    T_END_OF_STREAM = new EntityState("T_END_OF_STREAM", 13);
    EntityState[] arrayOfEntityState = new EntityState[14];
    arrayOfEntityState[0] = T_START_MESSAGE;
    arrayOfEntityState[1] = T_END_MESSAGE;
    arrayOfEntityState[2] = T_RAW_ENTITY;
    arrayOfEntityState[3] = T_START_HEADER;
    arrayOfEntityState[4] = T_FIELD;
    arrayOfEntityState[5] = T_END_HEADER;
    arrayOfEntityState[6] = T_START_MULTIPART;
    arrayOfEntityState[7] = T_END_MULTIPART;
    arrayOfEntityState[8] = T_PREAMBLE;
    arrayOfEntityState[9] = T_EPILOGUE;
    arrayOfEntityState[10] = T_START_BODYPART;
    arrayOfEntityState[11] = T_END_BODYPART;
    arrayOfEntityState[12] = T_BODY;
    arrayOfEntityState[13] = T_END_OF_STREAM;
    $VALUES = arrayOfEntityState;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.EntityState
 * JD-Core Version:    0.6.0
 */