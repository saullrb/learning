<?php

namespace DSA\ArrayList;

use InvalidArgumentException;
use OutOfBoundsException;
use UnderflowException;

class ArrayList
{
    private array $data;

    private int $size;

    public function __construct(private int $capacity = 4)
    {
        $this->size = 0;
        $this->data = array_fill(0, $this->capacity, null);
    }

    public function size(): int
    {
        return $this->size;
    }

    public function capacity(): int
    {
        return $this->capacity;
    }

    public function isEmpty(): bool
    {
        return $this->size === 0;
    }

    public function get(int $index): mixed
    {
        $this->checkOutOfBounds($index);

        return $this->data[$index];
    }

    public function set(int $index, mixed $item): void
    {
        $this->checkOutOfBounds($index);

        $this->data[$index] = $item;
    }

    public function append($item): void
    {
        $this->checkCapacityReached();

        $this->data[$this->size++] = $item;
    }

    public function pop(): mixed
    {
        $this->checkIsEmpty();

        $item = $this->data[--$this->size];
        unset($this->data[$this->size]);

        return $item;
    }

    public function insert(int $index, mixed $item): void
    {
        if ($index > $this->size || $index < 0) {
            throw new OutOfBoundsException("Index '$index' out of bounds");
        }

        $this->checkCapacityReached();
        $this->size++;

        for ($i = $this->size - 2; $i >= $index; $i--) {
            $this->data[$i + 1] = $this->data[$i];
        }

        $this->set($index, $item);
    }

    public function prepend(mixed $item): void
    {
        $this->insert(0, $item);
    }

    public function delete(int $index): void
    {
        $this->checkOutOfBounds($index);

        for ($i = $index; $i < $this->size - 1; $i++) {
            $this->set($i, $this->get($i + 1));
        }

        unset($this->data[--$this->size]);
    }

    public function remove(mixed $item): void
    {
        $index = $this->find($item);
        if ($index === -1) {
            throw new InvalidArgumentException("Item '$item' not found");
        }

        $this->delete($index);
    }

    public function find(mixed $item): int
    {
        for ($i = 0; $i < $this->size; $i++) {
            if ($this->get($i) === $item) {
                return $i;
            }
        }

        return -1;
    }

    private function resize(): void
    {
        $this->capacity *= 2;
        $newArrayList = array_fill(0, $this->capacity, null);

        for ($i = 0; $i < $this->size; $i++) {
            $newArrayList[$i] = $this->data[$i];
        }

        $this->data = $newArrayList;
    }

    private function checkOutOfBounds(int $index): void
    {
        if ($index >= $this->size || $index < 0) {
            throw new OutOfBoundsException("Index '$index' out of bounds");
        }
    }

    private function checkCapacityReached(): void
    {
        if ($this->size === $this->capacity) {
            $this->resize();
        }
    }

    private function checkIsEmpty(): void
    {
        if ($this->isEmpty()) {
            throw new UnderflowException('ArrayList is empty');
        }
    }
}
