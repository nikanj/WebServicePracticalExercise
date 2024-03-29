/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package de.tum.in.dss.service;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class addservice {

  public interface Iface {

    public List<GuestBook> retrieveGuestBook(GuestBook guestBook) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void retrieveGuestBook(GuestBook guestBook, org.apache.thrift.async.AsyncMethodCallback<AsyncClient.retrieveGuestBook_call> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public List<GuestBook> retrieveGuestBook(GuestBook guestBook) throws org.apache.thrift.TException
    {
      send_retrieveGuestBook(guestBook);
      return recv_retrieveGuestBook();
    }

    public void send_retrieveGuestBook(GuestBook guestBook) throws org.apache.thrift.TException
    {
      retrieveGuestBook_args args = new retrieveGuestBook_args();
      args.setGuestBook(guestBook);
      sendBase("retrieveGuestBook", args);
    }

    public List<GuestBook> recv_retrieveGuestBook() throws org.apache.thrift.TException
    {
      retrieveGuestBook_result result = new retrieveGuestBook_result();
      receiveBase(result, "retrieveGuestBook");
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "retrieveGuestBook failed: unknown result");
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void retrieveGuestBook(GuestBook guestBook, org.apache.thrift.async.AsyncMethodCallback<retrieveGuestBook_call> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      retrieveGuestBook_call method_call = new retrieveGuestBook_call(guestBook, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class retrieveGuestBook_call extends org.apache.thrift.async.TAsyncMethodCall {
      private GuestBook guestBook;
      public retrieveGuestBook_call(GuestBook guestBook, org.apache.thrift.async.AsyncMethodCallback<retrieveGuestBook_call> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.guestBook = guestBook;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("retrieveGuestBook", org.apache.thrift.protocol.TMessageType.CALL, 0));
        retrieveGuestBook_args args = new retrieveGuestBook_args();
        args.setGuestBook(guestBook);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public List<GuestBook> getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_retrieveGuestBook();
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("retrieveGuestBook", new retrieveGuestBook());
      return processMap;
    }

    public static class retrieveGuestBook<I extends Iface> extends org.apache.thrift.ProcessFunction<I, retrieveGuestBook_args> {
      public retrieveGuestBook() {
        super("retrieveGuestBook");
      }

      public retrieveGuestBook_args getEmptyArgsInstance() {
        return new retrieveGuestBook_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public retrieveGuestBook_result getResult(I iface, retrieveGuestBook_args args) throws org.apache.thrift.TException {
        retrieveGuestBook_result result = new retrieveGuestBook_result();
        result.success = iface.retrieveGuestBook(args.guestBook);
        return result;
      }
    }

  }

  public static class retrieveGuestBook_args implements org.apache.thrift.TBase<retrieveGuestBook_args, retrieveGuestBook_args._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("retrieveGuestBook_args");

    private static final org.apache.thrift.protocol.TField GUEST_BOOK_FIELD_DESC = new org.apache.thrift.protocol.TField("guestBook", org.apache.thrift.protocol.TType.STRUCT, (short)-1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new retrieveGuestBook_argsStandardSchemeFactory());
      schemes.put(TupleScheme.class, new retrieveGuestBook_argsTupleSchemeFactory());
    }

    public GuestBook guestBook; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      GUEST_BOOK((short)-1, "guestBook");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case -1: // GUEST_BOOK
            return GUEST_BOOK;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.GUEST_BOOK, new org.apache.thrift.meta_data.FieldMetaData("guestBook", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GuestBook.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(retrieveGuestBook_args.class, metaDataMap);
    }

    public retrieveGuestBook_args() {
    }

    public retrieveGuestBook_args(
      GuestBook guestBook)
    {
      this();
      this.guestBook = guestBook;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public retrieveGuestBook_args(retrieveGuestBook_args other) {
      if (other.isSetGuestBook()) {
        this.guestBook = new GuestBook(other.guestBook);
      }
    }

    public retrieveGuestBook_args deepCopy() {
      return new retrieveGuestBook_args(this);
    }

    public void clear() {
      this.guestBook = null;
    }

    public GuestBook getGuestBook() {
      return this.guestBook;
    }

    public retrieveGuestBook_args setGuestBook(GuestBook guestBook) {
      this.guestBook = guestBook;
      return this;
    }

    public void unsetGuestBook() {
      this.guestBook = null;
    }

    /** Returns true if field guestBook is set (has been assigned a value) and false otherwise */
    public boolean isSetGuestBook() {
      return this.guestBook != null;
    }

    public void setGuestBookIsSet(boolean value) {
      if (!value) {
        this.guestBook = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case GUEST_BOOK:
        if (value == null) {
          unsetGuestBook();
        } else {
          setGuestBook((GuestBook)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case GUEST_BOOK:
        return getGuestBook();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case GUEST_BOOK:
        return isSetGuestBook();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof retrieveGuestBook_args)
        return this.equals((retrieveGuestBook_args)that);
      return false;
    }

    public boolean equals(retrieveGuestBook_args that) {
      if (that == null)
        return false;

      boolean this_present_guestBook = true && this.isSetGuestBook();
      boolean that_present_guestBook = true && that.isSetGuestBook();
      if (this_present_guestBook || that_present_guestBook) {
        if (!(this_present_guestBook && that_present_guestBook))
          return false;
        if (!this.guestBook.equals(that.guestBook))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(retrieveGuestBook_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      retrieveGuestBook_args typedOther = (retrieveGuestBook_args)other;

      lastComparison = Boolean.valueOf(isSetGuestBook()).compareTo(typedOther.isSetGuestBook());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetGuestBook()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.guestBook, typedOther.guestBook);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("retrieveGuestBook_args(");
      boolean first = true;

      sb.append("guestBook:");
      if (this.guestBook == null) {
        sb.append("null");
      } else {
        sb.append(this.guestBook);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
      if (guestBook != null) {
        guestBook.validate();
      }
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class retrieveGuestBook_argsStandardSchemeFactory implements SchemeFactory {
      public retrieveGuestBook_argsStandardScheme getScheme() {
        return new retrieveGuestBook_argsStandardScheme();
      }
    }

    private static class retrieveGuestBook_argsStandardScheme extends StandardScheme<retrieveGuestBook_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, retrieveGuestBook_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case -1: // GUEST_BOOK
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.guestBook = new GuestBook();
                struct.guestBook.read(iprot);
                struct.setGuestBookIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, retrieveGuestBook_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.guestBook != null) {
          oprot.writeFieldBegin(GUEST_BOOK_FIELD_DESC);
          struct.guestBook.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class retrieveGuestBook_argsTupleSchemeFactory implements SchemeFactory {
      public retrieveGuestBook_argsTupleScheme getScheme() {
        return new retrieveGuestBook_argsTupleScheme();
      }
    }

    private static class retrieveGuestBook_argsTupleScheme extends TupleScheme<retrieveGuestBook_args> {

      public void write(org.apache.thrift.protocol.TProtocol prot, retrieveGuestBook_args struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetGuestBook()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetGuestBook()) {
          struct.guestBook.write(oprot);
        }
      }

      public void read(org.apache.thrift.protocol.TProtocol prot, retrieveGuestBook_args struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          struct.guestBook = new GuestBook();
          struct.guestBook.read(iprot);
          struct.setGuestBookIsSet(true);
        }
      }
    }

  }

  public static class retrieveGuestBook_result implements org.apache.thrift.TBase<retrieveGuestBook_result, retrieveGuestBook_result._Fields>, java.io.Serializable, Cloneable   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("retrieveGuestBook_result");

    private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.LIST, (short)0);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
    static {
      schemes.put(StandardScheme.class, new retrieveGuestBook_resultStandardSchemeFactory());
      schemes.put(TupleScheme.class, new retrieveGuestBook_resultTupleSchemeFactory());
    }

    public List<GuestBook> success; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      SUCCESS((short)0, "success");

      private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

      static {
        for (_Fields field : EnumSet.allOf(_Fields.class)) {
          byName.put(field.getFieldName(), field);
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, or null if its not found.
       */
      public static _Fields findByThriftId(int fieldId) {
        switch(fieldId) {
          case 0: // SUCCESS
            return SUCCESS;
          default:
            return null;
        }
      }

      /**
       * Find the _Fields constant that matches fieldId, throwing an exception
       * if it is not found.
       */
      public static _Fields findByThriftIdOrThrow(int fieldId) {
        _Fields fields = findByThriftId(fieldId);
        if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
        return fields;
      }

      /**
       * Find the _Fields constant that matches name, or null if its not found.
       */
      public static _Fields findByName(String name) {
        return byName.get(name);
      }

      private final short _thriftId;
      private final String _fieldName;

      _Fields(short thriftId, String fieldName) {
        _thriftId = thriftId;
        _fieldName = fieldName;
      }

      public short getThriftFieldId() {
        return _thriftId;
      }

      public String getFieldName() {
        return _fieldName;
      }
    }

    // isset id assignments
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT, 
          new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
              new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, GuestBook.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(retrieveGuestBook_result.class, metaDataMap);
    }

    public retrieveGuestBook_result() {
    }

    public retrieveGuestBook_result(
      List<GuestBook> success)
    {
      this();
      this.success = success;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public retrieveGuestBook_result(retrieveGuestBook_result other) {
      if (other.isSetSuccess()) {
        List<GuestBook> __this__success = new ArrayList<GuestBook>();
        for (GuestBook other_element : other.success) {
          __this__success.add(new GuestBook(other_element));
        }
        this.success = __this__success;
      }
    }

    public retrieveGuestBook_result deepCopy() {
      return new retrieveGuestBook_result(this);
    }

    public void clear() {
      this.success = null;
    }

    public int getSuccessSize() {
      return (this.success == null) ? 0 : this.success.size();
    }

    public java.util.Iterator<GuestBook> getSuccessIterator() {
      return (this.success == null) ? null : this.success.iterator();
    }

    public void addToSuccess(GuestBook elem) {
      if (this.success == null) {
        this.success = new ArrayList<GuestBook>();
      }
      this.success.add(elem);
    }

    public List<GuestBook> getSuccess() {
      return this.success;
    }

    public retrieveGuestBook_result setSuccess(List<GuestBook> success) {
      this.success = success;
      return this;
    }

    public void unsetSuccess() {
      this.success = null;
    }

    /** Returns true if field success is set (has been assigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return this.success != null;
    }

    public void setSuccessIsSet(boolean value) {
      if (!value) {
        this.success = null;
      }
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((List<GuestBook>)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return getSuccess();

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof retrieveGuestBook_result)
        return this.equals((retrieveGuestBook_result)that);
      return false;
    }

    public boolean equals(retrieveGuestBook_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true && this.isSetSuccess();
      boolean that_present_success = true && that.isSetSuccess();
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (!this.success.equals(that.success))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(retrieveGuestBook_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      retrieveGuestBook_result typedOther = (retrieveGuestBook_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, typedOther.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
      schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
      schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
      }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("retrieveGuestBook_result(");
      boolean first = true;

      sb.append("success:");
      if (this.success == null) {
        sb.append("null");
      } else {
        sb.append(this.success);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
      try {
        write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
      try {
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class retrieveGuestBook_resultStandardSchemeFactory implements SchemeFactory {
      public retrieveGuestBook_resultStandardScheme getScheme() {
        return new retrieveGuestBook_resultStandardScheme();
      }
    }

    private static class retrieveGuestBook_resultStandardScheme extends StandardScheme<retrieveGuestBook_result> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, retrieveGuestBook_result struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 0: // SUCCESS
              if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
                {
                  org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                  struct.success = new ArrayList<GuestBook>(_list8.size);
                  for (int _i9 = 0; _i9 < _list8.size; ++_i9)
                  {
                    GuestBook _elem10; // required
                    _elem10 = new GuestBook();
                    _elem10.read(iprot);
                    struct.success.add(_elem10);
                  }
                  iprot.readListEnd();
                }
                struct.setSuccessIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            default:
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
          }
          iprot.readFieldEnd();
        }
        iprot.readStructEnd();

        // check for required fields of primitive type, which can't be checked in the validate method
        struct.validate();
      }

      public void write(org.apache.thrift.protocol.TProtocol oprot, retrieveGuestBook_result struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.success != null) {
          oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.success.size()));
            for (GuestBook _iter11 : struct.success)
            {
              _iter11.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class retrieveGuestBook_resultTupleSchemeFactory implements SchemeFactory {
      public retrieveGuestBook_resultTupleScheme getScheme() {
        return new retrieveGuestBook_resultTupleScheme();
      }
    }

    private static class retrieveGuestBook_resultTupleScheme extends TupleScheme<retrieveGuestBook_result> {

      public void write(org.apache.thrift.protocol.TProtocol prot, retrieveGuestBook_result struct) throws org.apache.thrift.TException {
        TTupleProtocol oprot = (TTupleProtocol) prot;
        BitSet optionals = new BitSet();
        if (struct.isSetSuccess()) {
          optionals.set(0);
        }
        oprot.writeBitSet(optionals, 1);
        if (struct.isSetSuccess()) {
          {
            oprot.writeI32(struct.success.size());
            for (GuestBook _iter12 : struct.success)
            {
              _iter12.write(oprot);
            }
          }
        }
      }

      public void read(org.apache.thrift.protocol.TProtocol prot, retrieveGuestBook_result struct) throws org.apache.thrift.TException {
        TTupleProtocol iprot = (TTupleProtocol) prot;
        BitSet incoming = iprot.readBitSet(1);
        if (incoming.get(0)) {
          {
            org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
            struct.success = new ArrayList<GuestBook>(_list13.size);
            for (int _i14 = 0; _i14 < _list13.size; ++_i14)
            {
              GuestBook _elem15; // required
              _elem15 = new GuestBook();
              _elem15.read(iprot);
              struct.success.add(_elem15);
            }
          }
          struct.setSuccessIsSet(true);
        }
      }
    }

  }

}
