import unittest
from activities import eat, nap

class ActivityTests(unittest.TestCase):
    def test_eat_healthy(self):
        self.assertEqual(
            eat("broccoli", is_healthy=True),
            "I'm eating broccoli because my body is a temple"
        )
    def test_eat_unhealthy(self):
        self.assertEqual(
            eat("pizza", is_healthy=False),
            "I'm eathing pizza because YOLO"
        )

if __name__ == "__main__":
    unittest.main()
