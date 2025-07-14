<?php

use DSA\ArrayList\ArrayList;
use PHPUnit\Framework\TestCase;

class ArrayListTest extends TestCase
{
    public function test_append_and_get(): void
    {
        $list = new ArrayList;
        $list->append(1);
        $list->append(2);

        $this->assertSame(1, $list->get(0));
        $this->assertSame(2, $list->get(1));
    }

    public function test_size_and_capacity(): void
    {
        $list = new ArrayList;
        $this->assertSame(0, $list->size());
        $this->assertGreaterThanOrEqual(4, $list->capacity());
    }

    public function test_insert_and_prepend(): void
    {
        $list = new ArrayList;
        $list->append(1);
        $list->append(3);
        $list->insert(1, 2);
        $list->prepend(0);

        $this->assertSame(0, $list->get(0));
        $this->assertSame(1, $list->get(1));
        $this->assertSame(2, $list->get(2));
        $this->assertSame(3, $list->get(3));
    }

    public function test_delete_and_remove(): void
    {
        $list = new ArrayList;
        $list->append('a');
        $list->append('b');
        $list->append('c');

        $list->delete(1);
        $this->assertSame('a', $list->get(0));
        $this->assertSame('c', $list->get(1));
        $this->assertSame(2, $list->size());

        $list->remove('a');
        $this->assertSame('c', $list->get(0));
        $this->assertSame(1, $list->size());
    }

    public function test_remove_throws_on_missing(): void
    {
        $this->expectException(InvalidArgumentException::class);
        $list = new ArrayList;
        $list->remove('not-found');
    }

    public function test_pop(): void
    {
        $list = new ArrayList;
        $list->append(42);
        $value = $list->pop();

        $this->assertSame(42, $value);
        $this->assertTrue($list->isEmpty());
    }

    public function test_pop_throws_when_empty(): void
    {
        $this->expectException(UnderflowException::class);
        $list = new ArrayList;
        $list->pop();
    }

    public function test_find(): void
    {
        $list = new ArrayList;
        $list->append('x');
        $list->append('y');
        $this->assertSame(1, $list->find('y'));
        $this->assertSame(-1, $list->find('z'));
    }

    public function test_resize(): void
    {
        $list = new ArrayList(2);
        $list->append(1);
        $list->append(2);
        // triggers resize
        $list->append(3);

        $this->assertSame(3, $list->size());
        $this->assertGreaterThanOrEqual(4, $list->capacity());
        $this->assertSame(3, $list->get(2));
    }

    public function test_set_and_get(): void
    {
        $list = new ArrayList;
        $list->append(1);
        $list->set(0, 10);
        $this->assertSame(10, $list->get(0));
    }

    public function test_set_throws_out_of_bounds(): void
    {
        $this->expectException(OutOfBoundsException::class);
        $list = new ArrayList;
        $list->set(0, 'fail');
    }

    public function test_get_throws_out_of_bounds(): void
    {
        $this->expectException(OutOfBoundsException::class);
        $list = new ArrayList;
        $list->get(0);
    }

    public function test_delete_throws_out_of_bounds(): void
    {
        $this->expectException(OutOfBoundsException::class);
        $list = new ArrayList;
        $list->delete(0);
    }
}
