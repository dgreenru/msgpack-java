package org.msgpack.template.builder;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.packer.BufferPacker;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.Template;
import org.msgpack.testclasses.EnumTypeFieldsClass;
import org.msgpack.testclasses.EnumTypeFieldsClassNotNullable;
import org.msgpack.unpacker.Unpacker;


public class TestOrdinalEnumBufferPackUnpack extends TestSet {

    @Test @Override
    public void testEnumTypeFieldsClass() throws Exception {
	super.testEnumTypeFieldsClass();
    }

    @Override
    public void testEnumTypeFieldsClass(EnumTypeFieldsClass v) throws Exception {
	MessagePack msgpack = new MessagePack();
	TemplateRegistry registry = new TemplateRegistry(null);
	registry.register(EnumTypeFieldsClass.SampleEnum.class,
		new OrdinalEnumTemplateBuilder(registry).buildTemplate(EnumTypeFieldsClass.SampleEnum.class));
	ReflectionTemplateBuilder builder = new ReflectionTemplateBuilder(registry);
	Template<EnumTypeFieldsClass> tmpl = builder.buildTemplate(EnumTypeFieldsClass.class);
	BufferPacker packer = msgpack.createBufferPacker();
	tmpl.write(packer, v);
	byte[] bytes = packer.toByteArray();
	Unpacker unpacker = msgpack.createUnpacker(new ByteArrayInputStream(bytes));
	unpacker.resetReadByteCount();
	EnumTypeFieldsClass ret = tmpl.read(unpacker, null);
	assertEquals(v, ret);
	assertEquals(bytes.length, unpacker.getReadByteCount());
    }

    @Test @Override
    public void testEnumTypeFieldsClassNotNullable() throws Exception {
	super.testEnumTypeFieldsClassNotNullable();
    }

    @Override
    public void testEnumTypeFieldsClassNotNullable(EnumTypeFieldsClassNotNullable v) throws Exception {
	MessagePack msgpack = new MessagePack();
	TemplateRegistry registry = new TemplateRegistry(null);
	registry.register(EnumTypeFieldsClassNotNullable.SampleEnum.class,
		new OrdinalEnumTemplateBuilder(registry).buildTemplate(EnumTypeFieldsClassNotNullable.SampleEnum.class));
	ReflectionTemplateBuilder builder = new ReflectionTemplateBuilder(registry);
	Template<EnumTypeFieldsClassNotNullable> tmpl = builder.buildTemplate(EnumTypeFieldsClassNotNullable.class);
	BufferPacker packer = msgpack.createBufferPacker();
	tmpl.write(packer, v);
	byte[] bytes = packer.toByteArray();
	Unpacker unpacker = msgpack.createUnpacker(new ByteArrayInputStream(bytes));
	unpacker.resetReadByteCount();
	EnumTypeFieldsClassNotNullable ret = tmpl.read(unpacker, null);
	assertEquals(v, ret);
	assertEquals(bytes.length, unpacker.getReadByteCount());
    }
}
